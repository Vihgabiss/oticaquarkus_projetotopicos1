package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Oculos;

public record OculosResponseDTO(
    Long id,
    String referencia,
    String cor, 
    String tamanho
){

    public static OculosResponseDTO valueOf(Oculos oculos){
        return new OculosResponseDTO(
            oculos.getId(),
            oculos.getReferencia(),
            oculos.getCor(),
            oculos.getTamanho());
    }
}