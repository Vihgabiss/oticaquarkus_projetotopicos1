package br.unitins.topicos1.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.model.Venda;

public record VendaResponseDTO(
        Long id,
        LocalDateTime dataHora,
        UsuarioResponseDTO usuario,
        Double valorTotal,
        List<ItemVendaResponseDTO> itens,
        TipoPagamento tipoPagamento
) {
    public static VendaResponseDTO valueOf(Venda venda) {
        return new VendaResponseDTO(
            venda.getId(),
                venda.getDataHora(),
                UsuarioResponseDTO.valueOf(venda.getUsuario()),
                venda.getValorTotal(),
                ItemVendaResponseDTO.valueOf(venda.getItens()),
                venda.getTipoPagamento());
    }
}
