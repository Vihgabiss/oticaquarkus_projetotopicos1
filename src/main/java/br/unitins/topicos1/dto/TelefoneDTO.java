package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Telefone;

public record TelefoneDTO(
    String codigoArea,
    String numero
) {
    public static Telefone valueOf(TelefoneDTO dto){
        return new Telefone(
            dto.codigoArea(), 
            dto.numero()
        );
}
}
