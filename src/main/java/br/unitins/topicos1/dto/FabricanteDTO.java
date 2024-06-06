package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FabricanteDTO(
                @NotBlank(message = "O campo nome não pode ser nulo.") String nome,

                @NotBlank(message = "O campo telefone não pode ser nulo.") String telefone,

                @NotBlank(message = "O campo endereço não pode ser nulo.") String endereco,

                @NotBlank(message = "O campo email não pode ser nulo.") @Email(message = "Email inválido.") String email,

                @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message = "CNPJ inválido") String cnpj,

                @NotNull(message = "A lista de marcas não pode ser nula.") @Size(min = 1, message = "A lista de marcas deve conter pelo menos um item.") List<Long> listaMarca) {

}
