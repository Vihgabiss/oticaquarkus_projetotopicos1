package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.TipoPagamento;

public record TipoPagamentoResponseDTO(
        Long id,
        String nome) {
    public static TipoPagamentoResponseDTO valueOf(TipoPagamento tipoPagamento) {
        return new TipoPagamentoResponseDTO(
                tipoPagamento.getId(),
                tipoPagamento.getNome());
    }
}