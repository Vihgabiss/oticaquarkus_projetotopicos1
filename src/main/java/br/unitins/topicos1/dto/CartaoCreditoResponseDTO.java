package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.CartaoCredito;

public record CartaoCreditoResponseDTO(
        Long id,
        String numeroCartao,
        String nomeTitular,
        String validade,
        String cvv,
        Integer parcelas){

public static CartaoCreditoResponseDTO valueOf(CartaoCredito cartao) {
        return new CartaoCreditoResponseDTO(
                cartao.getId(),
                cartao.getNumeroCartao(),
                cartao.getNomeTitular(),
                cartao.getValidade(),
                cartao.getCvv(),
                cartao.getParcelas());
}
}