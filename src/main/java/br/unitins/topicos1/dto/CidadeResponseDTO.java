package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cidade;

public record CidadeResponseDTO(
    Long id,
    String nome
) {
   public static CidadeResponseDTO valueOf(Cidade cidade){
    return new CidadeResponseDTO(
        cidade.getId(),
        cidade.getNome()
    );
   } 
}
