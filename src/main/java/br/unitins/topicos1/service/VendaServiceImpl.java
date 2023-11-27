package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.ItemVendaDTO;
import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;
import br.unitins.topicos1.model.ItemVenda;
import br.unitins.topicos1.model.Oculos;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.OculosRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.repository.VendaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VendaServiceImpl implements VendaService {

    @Inject
    OculosRepository oculosRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    VendaRepository vendaRepository;

    @Override
    @Transactional
    public VendaResponseDTO insert(VendaDTO dto, String email) {
        Venda venda = new Venda();

        venda.setDataHora(LocalDateTime.now());

        Double total = 0.0;
        for (ItemVendaDTO itemDto : dto.itens()) {
            total += (itemDto.preco() * itemDto.quantidade());
        }
        venda.setValorTotal(total);

        venda.setTipoPagamento(TipoPagamento.valueOf(dto.idTipoPagamento()));

        venda.setItens(new ArrayList<ItemVenda>());
        for (ItemVendaDTO itemDto : dto.itens()) {
            ItemVenda item = new ItemVenda();
            item.setPreco(itemDto.preco());
            item.setQuantidade(itemDto.quantidade());
            item.setVenda(venda);
            Oculos oculos = oculosRepository.findById(itemDto.idProduto());
            item.setOculos(oculos);

            oculos.setQuantidade(oculos.getQuantidade() - item.getQuantidade());

            venda.getItens().add(item);
        }

        venda.setUsuario(usuarioRepository.findByEmail(email));

        return VendaResponseDTO.valueOf(venda);
    }

    @Override
    public VendaResponseDTO findById(Long id) {
        return VendaResponseDTO.valueOf(vendaRepository.findById(id));
    }

    @Override
    public List<VendaResponseDTO> findByAll() {
        return vendaRepository.listAll().stream()
                .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<VendaResponseDTO> findByAll(String login) {
        return vendaRepository.listAll().stream()
                .map(e -> VendaResponseDTO.valueOf(e)).toList();
    }
}