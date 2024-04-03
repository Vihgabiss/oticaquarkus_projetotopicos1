package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.EstiloOculos;

public record EstiloOculosResponseDTO(
    Long id,
    String nome,
    String descricao) {
    
            public static EstiloOculosResponseDTO valueOf(EstiloOculos estiloOculos){
                return new EstiloOculosResponseDTO(
                    estiloOculos.getId(),
                    estiloOculos.getNome(), 
                    estiloOculos.getDescricao());
            }
}
