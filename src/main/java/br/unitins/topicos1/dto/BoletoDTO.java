package br.unitins.topicos1.dto;

import java.time.LocalDate;

import org.apache.commons.lang3.RandomStringUtils;

public record BoletoDTO(
        String codigoBarras, // Adiciona o campo para o código de barras
        LocalDate dataVencimento) {
    public BoletoDTO() { // Construtor para gerar dados aleatórios
        this(RandomStringUtils.randomNumeric(44), // Código de barras aleatório
                LocalDate.now().plusDays(3)); // Data de vencimento aleatória
    }
}
