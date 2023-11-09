package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(

    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O cpf não pode ser nulo.")
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)", message = "Preencha o cpf de acordo com o formato xxx.xxx.xxx-xx")
    String cpf,
    String email,
    String senha,
    Integer idPerfil,
    List<TelefoneResponseDTO> listaTelefone

) {
    
}
