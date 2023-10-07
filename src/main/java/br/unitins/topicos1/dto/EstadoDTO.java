package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Estado;

public record EstadoDTO( 
     String nome,
     String sigla
) {
         public static EstadoDTO valueOf(Estado estado){
        return new EstadoDTO(
            estado.getNome(),
            estado.getSigla()
);
    }
}
