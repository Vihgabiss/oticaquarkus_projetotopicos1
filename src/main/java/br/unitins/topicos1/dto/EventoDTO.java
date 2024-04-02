package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record EventoDTO(
        @NotBlank(message = "O campo descrição não pode ser nulo.") 
        String descricao,
        @NotBlank(message = "O campo nome não pode ser nulo.")
         String nome) {
}
