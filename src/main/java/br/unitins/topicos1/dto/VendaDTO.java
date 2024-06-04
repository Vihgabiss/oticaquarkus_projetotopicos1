package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record VendaDTO(
       List<ItemVendaDTO> itens,

        String cupom,

        @NotNull(message = "O id do endereço não pode ser nulo")
        Long idEnderecoEntrega) {

}
