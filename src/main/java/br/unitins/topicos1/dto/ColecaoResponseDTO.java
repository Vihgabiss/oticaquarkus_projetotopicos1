package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Colecao;

public record ColecaoResponseDTO(
    Long id,
    String nome,
    String descricao,
    LocalDate dataLancamento) {
    
        public static ColecaoResponseDTO valueOf(Colecao colecao){
            return new ColecaoResponseDTO(
                colecao.getId(),
                colecao.getNome(),
                colecao.getDescricao(),
                colecao.getDataLancamento());
        }
}
