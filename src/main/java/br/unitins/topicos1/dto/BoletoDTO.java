package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BoletoDTO(
        @NotBlank(message = "O código de barras não pode ser vazio")
        @Size(max = 12) 
        String codigoBarras,

        @NotNull(message = "A data de vencimento não pode ser nula") 
        @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$", message = "Formato de data inválido. Formato esperado dd/MM/yyyy") LocalDate dataVencimento) {

}
