package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTO(
    @NotBlank(message = "Nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "Sigla não pode estar em branco.")
    @Size(min = 2, max = 2)
    String sigla
) {

} 
