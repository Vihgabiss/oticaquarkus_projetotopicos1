package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.StatusVenda;

public record VendaDTO(
        Integer idTipoPagamento,
        List<ItemVendaDTO> itens,
        StatusVenda statusVenda) {

}
