package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record CidadeDTO(
    @NotBlank(message = "O campo cidade deve ser preenchido.")
    String nome
) {
    
}
