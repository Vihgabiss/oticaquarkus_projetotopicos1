package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Cupom;

public record CupomResponseDTO(
    Long id,
    String nome,
    String descricao,
    LocalDate dataLancamento,
    EventoResponseDTO evento
) {
    public static CupomResponseDTO valueOf(Cupom cupom){
    return new CupomResponseDTO(
        cupom.getId(),
        cupom.getNome(),
        cupom.getDescricao(),
        cupom.getDataLancamento(),
        EventoResponseDTO.valueOf(cupom.getIdevento())
    );
}
}
