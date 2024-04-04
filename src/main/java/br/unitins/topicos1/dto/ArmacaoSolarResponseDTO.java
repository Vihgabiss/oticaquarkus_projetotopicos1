package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ArmacaoSolar;

public record ArmacaoSolarResponseDTO(
        Long id,
        String tipoLenteSolar

) {
    public static ArmacaoSolarResponseDTO valueOf(ArmacaoSolar armacaoSolar) {
        return new ArmacaoSolarResponseDTO(
                armacaoSolar.getId(),
                armacaoSolar.getTipoLenteSolar().getLabel());
    }
}
