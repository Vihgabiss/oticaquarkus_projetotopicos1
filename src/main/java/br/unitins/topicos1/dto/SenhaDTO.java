package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SenhaDTO(
    @NotBlank(message = "A senha é obrigatória") 
    @Size(min = 3, message = "A senha deve ter pelo menos 3 dígitos")
    String senhaAtual,

    @NotBlank(message = "A senha é obrigatória") 
    @Size(min = 3, message = "A senha deve ter pelo menos 3 dígitos")
    String senhaNova,

    @NotBlank(message = "A senha é obrigatória") 
    @Size(min = 3, message = "A senha deve ter pelo menos 3 dígitos")
    String senhaConfirmada
) {
    
}
