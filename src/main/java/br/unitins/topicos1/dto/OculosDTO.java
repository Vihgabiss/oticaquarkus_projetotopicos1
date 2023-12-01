package br.unitins.topicos1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record OculosDTO(
                @NotBlank(message = "O campo referência não pode ser nulo.") 
                String referencia,

                @NotBlank(message = "O campo nome não pode ser nulo.") 
                @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Cor inválida.") 
                String cor,

                @NotNull(message = "O tamanho não pode ser nulo.") 
                String tamanho,

                @NotNull(message = "O precoCusto tamanho não pode ser nulo.")
                @Positive(message = "O valor deve ser positivo") 
                Double precoCusto,

                @NotNull(message = "O campo precoVenda não pode ser nulo.")
                @Positive(message = "O valor deve ser positivo") 
                Double precoVenda,

                @NotNull(message = "Quantidade cannot be null")
                @Min(value = 1, message = "Quantidade deve ser maior ou igual a um") 
                Integer quantidade,

                MarcaResponseDTO marca,

                @NotNull(message = "O id do tipo do óculos não pode ser nulo.") 
                Integer idTipoOculos,

                @NotNull(message = "O nome da imagem não pode ser nulo.") 
                String nomeImagem) {

}