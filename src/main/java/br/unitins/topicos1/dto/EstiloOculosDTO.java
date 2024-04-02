package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record EstiloOculosDTO(
    
    @NotBlank(message = "Nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "Descrição não pode ser nulo.")
    String descricao
) {
    
}
