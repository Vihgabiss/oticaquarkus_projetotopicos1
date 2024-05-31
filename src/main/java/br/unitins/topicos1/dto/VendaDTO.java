package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record VendaDTO(
        @NotNull(message = "O id do tipo de pagamento não pode ser nulo")
        Long idTipoPagamento,

        List<ItemVendaDTO> itens,

        String cupom) {

}
