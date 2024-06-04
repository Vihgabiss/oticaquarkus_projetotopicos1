package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento;

public record PagamentoResponseDTO(
        Long id,
        String tipoPagamento) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getTipoPagamento().getLabel());
    }
}