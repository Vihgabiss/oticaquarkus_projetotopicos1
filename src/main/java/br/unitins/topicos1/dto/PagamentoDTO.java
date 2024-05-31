package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;

public record PagamentoDTO(
    Long id, 
    @NotNull(message = "O tipo de pagamento não pode ser nulo")
    Long tipoPagamentoId 
) {}
