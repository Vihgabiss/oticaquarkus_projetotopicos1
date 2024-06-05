package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Boleto;

public record BoletoResponseDTO(
        Long id,
        String codigoBarras,
        LocalDate dataVencimento) {
    public static BoletoResponseDTO valueOf(Boleto boleto) {
        return new BoletoResponseDTO(
                boleto.getId(),
                boleto.getCodigoBarras(),
                boleto.getDataVencimento());

    }
}