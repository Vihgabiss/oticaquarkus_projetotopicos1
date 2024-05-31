package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.unitins.topicos1.model.BoletoFactory;
import br.unitins.topicos1.model.CartaoCredito;
import br.unitins.topicos1.model.CartaoDebito;
import br.unitins.topicos1.model.Cupom;
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
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.PixRepository;
import br.unitins.topicos1.repository.TipoPagamentoRepository;
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
    TipoPagamentoRepository tipoPagamentoRepository;

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

        TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(dto.idTipoPagamento()); // Fetch TipoPagamento
                                                                                               // entity

        if (tipoPagamento == null) {
            throw new RuntimeException("Tipo de pagamento inválido: " + dto.idTipoPagamento());
        }

        venda.setTipoPagamento(tipoPagamento);

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
                double valorDesconto = (venda.getValorTotal() * cupom.getPorcentagemDesconto()) / 100.0;
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
    public VendaResponseDTO realizarPagamentoBoleto(Long vendaId, BoletoDTO boletoDTO, TipoPagamento tipoPagamento) {
        Optional<Venda> optionalVenda = vendaRepository.findByIdOptional(vendaId);
        if (optionalVenda.isEmpty()) {
            throw new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId);
        }

        Venda venda = optionalVenda.get();

        // Cria o boleto (usando o BoletoFactory)
        Boleto boleto = BoletoFactory.criarBoleto(boletoDTO.codigoBarras());

        // Persiste o boleto no banco de dados
        boletoRepository.persist(boleto);

        // Associa o pagamento à venda
        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setBoleto(boleto);
        pagamentoRepository.persist(pagamento);

        // Atualiza o status da venda para "PAGAMENTO_CONFIRMADO"
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

    @Override
    @Transactional
    public VendaResponseDTO realizarPagamentoPix(Long vendaId, PixDTO pixDTO, TipoPagamento tipoPagamento) {
        Optional<Venda> optionalVenda = vendaRepository.findByIdOptional(vendaId);
        if (optionalVenda.isEmpty()) {
            throw new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId);
        }

        Venda venda = optionalVenda.get();

        // Cria o pix
        Pix pix = new Pix();
        pix.setChavePix(pixDTO.chavePix());
        pix.setQrCode(pixDTO.qrCode());

        // Persiste o pix no banco de dados
        pixRepository.persist(pix);

        // Associa o pagamento à venda
        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setPix(pix);
        pagamentoRepository.persist(pagamento);

        // Atualiza o status da venda para "PAGAMENTO_CONFIRMADO"
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

    @Override
    @Transactional
    public VendaResponseDTO realizarPagamentoCartaoCredito(Long vendaId, CartaoCreditoDTO cartaoCreditoDTO,
            TipoPagamento tipoPagamento) {
        Optional<Venda> optionalVenda = vendaRepository.findByIdOptional(vendaId);
        if (optionalVenda.isEmpty()) {
            throw new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId);
        }

        Venda venda = optionalVenda.get();

        // Cria o cartão de crédito
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setNumeroCartao(cartaoCreditoDTO.numeroCartao());
        cartaoCredito.setNomeTitular(cartaoCreditoDTO.nomeTitular());
        cartaoCredito.setValidade(cartaoCreditoDTO.validade());
        cartaoCredito.setCvv(cartaoCreditoDTO.cvv());
        cartaoCredito.setParcelas(cartaoCreditoDTO.parcelas());

        // Persiste o cartão de crédito no banco de dados
        cartaoCreditoRepository.persist(cartaoCredito);

        // Associa o pagamento à venda
        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setCartaoCredito(cartaoCredito);
        pagamentoRepository.persist(pagamento);

        // Atualiza o status da venda para "PAGAMENTO_CONFIRMADO"
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

    @Override
    @Transactional
    public VendaResponseDTO realizarPagamentoCartaoDebito(Long vendaId, CartaoDebitoDTO cartaoDebitoDTO,
            TipoPagamento tipoPagamento) {
        Optional<Venda> optionalVenda = vendaRepository.findByIdOptional(vendaId);
        if (optionalVenda.isEmpty()) {
            throw new EntityNotFoundException("Venda não encontrada com o ID: " + vendaId);
        }

        Venda venda = optionalVenda.get();

        // Cria o cartão de débito
        CartaoDebito cartaoDebito = new CartaoDebito();
        cartaoDebito.setNumeroCartao(cartaoDebitoDTO.numeroCartao());
        cartaoDebito.setNomeTitular(cartaoDebitoDTO.nomeTitular());
        cartaoDebito.setValidade(cartaoDebitoDTO.validade());
        cartaoDebito.setCvv(cartaoDebitoDTO.cvv());

        // Persiste o cartão de débito no banco de dados
        cartaoDebitoRepository.persist(cartaoDebito);

        // Associa o pagamento à venda
        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setCartaoDebito(cartaoDebito);
        pagamentoRepository.persist(pagamento);

        // Atualiza o status da venda para "PAGAMENTO_CONFIRMADO"
        venda.setStatusVenda(StatusVenda.PAGAMENTO_CONFIRMADO);
        vendaRepository.persist(venda);

        return VendaResponseDTO.valueOf(venda);
    }

}