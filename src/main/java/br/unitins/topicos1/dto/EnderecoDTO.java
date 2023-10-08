package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
    @NotBlank(message = "O cep não pode ser nulo.")
    @Pattern(regexp = "(^\\d{5}-\\d{3}$)", message = "Preencha o cep de acordo com o formato xxxxx-xxx")
    String cep,

    @NotBlank(message = "Insira o bairro.")  
    String bairro,

    @NotBlank(message = "Insira a rua/alameda.") 
    String rua, 

    @NotBlank(message = "Insira o numero.") 
    Integer numero,

    String complemento,

    @NotBlank(message = "Insira a Cidade.") 
    String cidade
) {
    
}
