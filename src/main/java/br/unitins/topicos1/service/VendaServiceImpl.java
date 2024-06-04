package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.dto.BoletoDTO;
import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.CartaoDebitoDTO;
import br.unitins.topicos1.dto.ItemVendaDTO;
import br.unitins.topicos1.dto.ItemVendaResponseDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Boleto;
import br.unitins.topicos1.model.CartaoCredito;
import br.unitins.topicos1.model.CartaoDebito;
import br.unitins.topicos1.model.Cupom;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.ItemVenda;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Pix;
import br.unitins.topicos1.model.StatusVenda;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.BoletoRepository;
import br.unitins.topicos1.repository.CartaoCreditoRepository;
import br.unitins.topicos1.repository.CartaoDebitoRepository;
import br.unitins.topicos1.repository.CupomRepository;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.PixRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.repository.VendaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VendaServiceImpl implements VendaService {

    @Inject
    ArmacaoRepository armacaoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    VendaRepository vendaRepository;

    @Inject
    CupomRepository cupomRepository;

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    BoletoRepository boletoRepository;

    @Inject
    CartaoCreditoRepository cartaoCreditoRepository;

    @Inject
    CartaoDebitoRepository cartaoDebitoRepository;

    @Inject
    PixRepository pixRepository;

    @Override
    @Transactional
    public VendaResponseDTO insert(VendaDTO dto, String email) {
        Venda venda = new Venda();

        venda.setDataHora(LocalDateTime.now());

        Double total = 0.0;
        for (ItemVendaDTO itemDto : dto.itens()) {
            Armacao armacao = armacaoRepository.findById(itemDto.idProduto());
            total += (armacao.getPrecoVenda() * itemDto.quantidade());
        }

        venda.setValorTotal(total);

        Endereco endereco = enderecoRepository.findById(dto.idEnderecoEntrega());
        if (endereco == null) {
            throw new RuntimeException("Endereco inválido: " + dto.idEnderecoEntrega());
        }
        venda.setEnderecoEntrega(endereco);

        venda.setStatusVenda(StatusVenda.AGUARDANDO_PAGAMENTO);

        venda.setItens(new ArrayList<ItemVenda>());

        for (ItemVendaDTO itemDto : dto.itens()) {
            ItemVenda item = new ItemVenda();
            item.setQuantidade(itemDto.quantidade());
            item.setVenda(venda);

            Armacao armacao = armacaoRepository.findById(itemDto.idProduto());
            item.setPreco(ArmacaoResponseDTO.valueOf(armacao).precoVenda());
            item.setArmacao(armacao);

            armacao.setQuantidade(armacao.getQuantidade() - item.getQuantidade());

            venda.getItens().add(item);
        }

        // adicionando o valor do cupom
        if (dto.cupom() != null && !dto.cupom().isEmpty()) {
            Cupom cupom = cupomRepository.findByNome(dto.cupom());
            if (cupom != null) {
                venda.setCupom(cupom);

                // Aplicar o desconto do cupom (porcentagem)
                double valorDesconto = (venda.getValorTotal() * 10) / 100.0;
                total -= valorDesconto; // Subtrai o valor do desconto do total
                venda.setValorTotal(total);
            } else {
                throw new RuntimeException("Cupom inválido: " + dto.cupom());
            }
        }

        venda.setUsuario(usuarioRepository.findByEmail(email));

        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

    public List<VendaResponseDTO> findByAll() {
        return vendaRepository.listAll().stream()
                .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<VendaResponseDTO> findByAll(String email) {
        return vendaRepository.findAllByEmail(email).stream()
                .map(e -> VendaResponseDTO.valueOf(e))
                .toList();
    }

    @Override
    public VendaResponseDTO findById(Long id) {
        return VendaResponseDTO.valueOf(vendaRepository.findById(id));
    }

    @Override
    public VendaResponseDTO findItemVendaById(Long id) {
        Venda venda = vendaRepository.findById(id);

        if (venda != null) {
            VendaResponseDTO vendaResponseDTO = VendaResponseDTO.valueOf(venda);

            vendaResponseDTO.itens(ItemVendaResponseDTO.valueOf(venda.getItens()));

            return vendaResponseDTO;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public VendaResponseDTO editStatusVenda(Long id, Integer novoStatusId) {
        Venda venda = vendaRepository.findById(id);
        if (venda == null) {
            throw new RuntimeException("Venda não encontrada com o ID: " + id);
        }

        try {
            StatusVenda novoStatus = StatusVenda.valueOf(novoStatusId);
            venda.setStatusVenda(novoStatus);
            vendaRepository.persist(venda);
            return VendaResponseDTO.valueOf(venda);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status de venda inválido: " + novoStatusId);
        }
    }

    @Override
    @Transactional
    public VendaResponseDTO realizarPagamentoBoleto(Long vendaId, BoletoDTO boletoDTO) {
        Venda venda = vendaRepository.findByIdOptional(vendaId)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId));

        Boleto boleto = new Boleto(boletoDTO.codigoBarras());
        boleto.setDataVencimento(boletoDTO.dataVencimento());

        // Criar e persistir o pagamento com o tipo de pagamento associado
        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setTipoPagamento(TipoPagamento.BOLETO); // Define o tipo de pagamento como BOLETO
        pagamento.setBoleto(boleto);
        pagamentoRepository.persist(pagamento);

        venda.setPagamento(pagamento);
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

    @Override
    @Transactional
    public VendaResponseDTO realizarPagamentoPix(Long vendaId, PixDTO pixDTO) {
        Venda venda = vendaRepository.findByIdOptional(vendaId)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId));

        Pix pix = new Pix();
        // Verificar se a chave PIX já existe
        Pix existingPix = pixRepository.findByChavePix(pix.getChavePix());
        if (existingPix != null) {
            throw new RuntimeException("Chave PIX já cadastrada.");
        }

        pixRepository.persist(pix);

        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setPix(pix);
        pagamento.setTipoPagamento(TipoPagamento.PIX); // Define o tipo de pagamento como PIX
        pagamentoRepository.persist(pagamento);

        venda.setPagamento(pagamento);
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

    @Override
    @Transactional
    public VendaResponseDTO realizarPagamentoCartaoCredito(Long vendaId, CartaoCreditoDTO cartaoCreditoDTO) {
        Venda venda = vendaRepository.findByIdOptional(vendaId)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId));

        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setNumeroCartao(cartaoCreditoDTO.numeroCartao());
        cartaoCredito.setNomeTitular(cartaoCreditoDTO.nomeTitular());
        cartaoCredito.setValidade(cartaoCreditoDTO.validade());
        cartaoCredito.setCvv(cartaoCreditoDTO.cvv());
        cartaoCredito.setParcelas(cartaoCreditoDTO.parcelas());

        cartaoCreditoRepository.persist(cartaoCredito);

        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setCartaoCredito(cartaoCredito);
        pagamento.setTipoPagamento(TipoPagamento.CREDITO); // Define o tipo de pagamento como CARTÃO DE CRÉDITO
        pagamentoRepository.persist(pagamento);

        venda.setPagamento(pagamento);
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

    @Override
    @Transactional
    public VendaResponseDTO realizarPagamentoCartaoDebito(Long vendaId, CartaoDebitoDTO cartaoDebitoDTO) {
        Venda venda = vendaRepository.findByIdOptional(vendaId)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId));

        CartaoDebito cartaoDebito = new CartaoDebito();
        cartaoDebito.setNumeroCartao(cartaoDebitoDTO.numeroCartao());
        cartaoDebito.setNomeTitular(cartaoDebitoDTO.nomeTitular());
        cartaoDebito.setValidade(cartaoDebitoDTO.validade());
        cartaoDebito.setCvv(cartaoDebitoDTO.cvv());

        cartaoDebitoRepository.persist(cartaoDebito);

        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setCartaoDebito(cartaoDebito);
        pagamento.setTipoPagamento(TipoPagamento.DEBITO); // Define o tipo de pagamento como CARTÃO DE DÉBITO
        pagamentoRepository.persist(pagamento);

        venda.setPagamento(pagamento);
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

}