package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CartaoDebitoDTO(
                @NotBlank(message = "O número do cartão não pode ser vazio") 
                @Size(max = 12) 
                String numeroCartao,

                @NotBlank(message = "O nome do titular não pode ser vazio")
                String nomeTitular,

                @NotBlank(message = "A validade não pode ser vazia")
                @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "A validade deve estar no formato MM/AA") 
                String validade,

                @NotBlank(message = "O CVV não pode ser vazio") 
                @Size(min = 3, max = 3) 
                String cvv

) {

}
