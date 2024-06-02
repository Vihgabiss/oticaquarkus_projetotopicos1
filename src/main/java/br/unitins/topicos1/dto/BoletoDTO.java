package br.unitins.topicos1.dto;

import java.time.LocalDate;

public record BoletoDTO(
    LocalDate dataVencimento
    ) {}