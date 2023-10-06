package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String  cpf,
    String email,
    List<TelefoneDTO> listaTelefone
   // List<EnderecoDTO> listaEndereco
) {
    
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getListaTelefone()
                .stream()
                .map(t -> TelefoneDTO.valueOf(t)).toList()
           /*  usuario.getListaEndereco()
                .stream()
                .map(e -> EnderecoDTO.valueOf(e)).toList()*/
        );
    }

}
