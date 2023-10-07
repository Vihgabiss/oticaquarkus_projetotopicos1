package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.model.Estado;

public record CidadeDTO(

        String nome,
        Estado estado
        ) {
            public static CidadeDTO valueOf(Cidade cidade){
                return new CidadeDTO(
                    cidade.getNome(),
                    cidade.getEstado()
                );
            }
}
