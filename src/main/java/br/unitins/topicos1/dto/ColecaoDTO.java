package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record ColecaoDTO(

    @NotBlank(message = "Nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "A descrição não pode estar em branco.")
    String descricao,
    
    LocalDate dataLancamento
) {
    
}
