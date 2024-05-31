package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BoletoDTO(
                @NotBlank(message = "O código de barras não pode ser vazio") @Size(max = 12) String codigoBarras) {

}
