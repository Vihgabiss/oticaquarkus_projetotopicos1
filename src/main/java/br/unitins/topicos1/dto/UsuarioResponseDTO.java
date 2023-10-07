package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String  cpf,
    String email,
    List<TelefoneResponseDTO> listaTelefone 
) {
    
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getListaTelefone()
                .stream()
                .map(t -> TelefoneResponseDTO.valueOf(t)).toList()
        );
    }

}
