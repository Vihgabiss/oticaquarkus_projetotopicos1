package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record TelefoneDTO(
    @NotBlank(message = "O campo código de área não pode ser nulo.")
    @Size(min = 2, message = "O código de área precisa ter pelo menos 2 dígitos")
    String codigoArea,

    @NotBlank(message = "Insira um número")
    @Pattern(regexp = "(^\\d{4}\\-\\d{4}$)", message = "Preencha o numero de acordo com o formato xxxx-xxxx")
    String numero
) {
    public static Telefone valueOf(TelefoneDTO dto){
        return new Telefone(
            dto.codigoArea(), 
            dto.numero()
        );
}

    public Long getId() {
        return null;
    }
}
