package br.unitins.topicos1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "O campo email deve ser preenchido.")
         @Email(message = "E-mail inválido") 
         String email,
        @NotBlank(message = "A senha é obrigatória") 
        @Size(min = 3, message = "A senha deve ter pelo menos 3 dígitos") 
        String senha) {

}
