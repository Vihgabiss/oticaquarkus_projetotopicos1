package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoPagamentoDTO(
        Long id,
        @NotBlank(message = "O campo nome não pode ser nulo.") String nome) {

}
