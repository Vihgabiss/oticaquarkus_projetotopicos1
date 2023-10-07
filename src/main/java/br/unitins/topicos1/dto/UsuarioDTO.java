package br.unitins.topicos1.dto;

import java.util.List;

public record UsuarioDTO(
    String nome,
    String cpf,
    String email,
    String senha,
    List<TelefoneResponseDTO> listaTelefone

) {
    
}
