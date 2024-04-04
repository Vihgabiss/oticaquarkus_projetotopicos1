package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(

    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,

    @NotBlank(message = "O cpf não pode ser nulo.")
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)", message = "Preencha o cpf de acordo com o formato xxx.xxx.xxx-xx")
    String cpf,

    @NotBlank(message = "O campo email deve ser preenchido.")
    @Email(message = "E-mail inválido") 
    String email,

    @NotBlank(message = "A senha é obrigatória") 
    @Size(min = 3, message = "A senha deve ter pelo menos 3 dígitos") 
    String senha,

    Integer idPerfil,
    List<TelefoneDTO> listaTelefone

) {
    
}