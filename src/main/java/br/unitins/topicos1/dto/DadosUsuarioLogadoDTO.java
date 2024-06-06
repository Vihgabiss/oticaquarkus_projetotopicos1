package br.unitins.topicos1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosUsuarioLogadoDTO(
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,

    @NotBlank(message = "O campo email deve ser preenchido.")
    @Email(message = "E-mail inválido") 
    String email
){
}
