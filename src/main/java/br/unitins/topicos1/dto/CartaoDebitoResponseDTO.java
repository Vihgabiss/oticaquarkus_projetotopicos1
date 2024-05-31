package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.CartaoDebito;

public record CartaoDebitoResponseDTO(
        Long id,
        String numeroCartao,
        String nomeTitular,
        String validade,
        String cvv) {
    public static CartaoDebitoResponseDTO valueOf(CartaoDebito cartao) {
        return new CartaoDebitoResponseDTO(
                cartao.getId(),
                cartao.getNumeroCartao(),
                cartao.getNomeTitular(),
                cartao.getValidade(),
                cartao.getCvv());
    }
}
