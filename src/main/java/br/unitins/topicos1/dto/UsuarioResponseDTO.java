package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
                Long id,
                String nome,
                String cpf,
                String email,
                Perfil perfil,
                List<TelefoneResponseDTO> listaTelefone,
                List<EnderecoResponseDTO> listaEndereco,
                String nomeImagem) {

        public static UsuarioResponseDTO valueOf(Usuario usuario) {
                return new UsuarioResponseDTO(
                                usuario.getId(),
                                usuario.getNome(),
                                usuario.getCpf(),
                                usuario.getEmail(),
                                usuario.getPerfil(),
                                usuario.getListaTelefone()
                                                .stream()
                                                .map(t -> TelefoneResponseDTO.valueOf(t)).toList(),
                                usuario.getListaEndereco()
                                                .stream()
                                                .map(e -> EnderecoResponseDTO.valueOf(e)).toList(),
                                usuario.getNomeImagem());
        }

}