package br.unitins.topicos1.dto;

import java.util.List;

public record VendaDTO(
        Integer idTipoPagamento,
        List<ItemVendaDTO> itens) {

}
