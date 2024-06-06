package br.unitins.topicos1.dto;

import java.util.List;

public record VendaDTO(
        // @NotNull(message = "O id do tipo de pagamento não pode ser nulo")
         Long idTipoPagamento,

        List<ItemVendaDTO> itens,

        String cupom,

        // @NotNull(message = "O id do endereço não pode ser nulo") 
        Long idEnderecoEntrega) {

}