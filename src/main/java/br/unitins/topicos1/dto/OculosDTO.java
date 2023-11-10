package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OculosDTO(
                @NotBlank(message = "O campo referência não pode ser nulo.")
                 String referencia,

                @NotBlank(message = "O campo nome não pode ser nulo.")
                 @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Cor inválida.") 
                 String cor,

                // @NotNull(message = "O taamanho tamanho não pode ser nulo.")
                 String tamanho,

                @NotNull(message = "O precoCusto tamanho não pode ser nulo.")
                 @Positive(message = "O valor deve ser positivo") 
                 Double precoCusto,

                @NotNull(message = "O campo precoVenda não pode ser nulo.")
                 @Positive(message = "O valor deve ser positivo") 
                 Double precoVenda,

                @PositiveOrZero(message = "O valor deve ser positivo ou zero") 
                @NotBlank(message = "O campo quantidade não pode ser nulo.") 
                Integer quantidade,

                MarcaResponseDTO marca) {

}
