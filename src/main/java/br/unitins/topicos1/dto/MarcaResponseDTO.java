package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Marca;

public record MarcaResponseDTO(
                Long id,
                String nome,
                FornecedorResponseDTO fornecedor) {
        public static MarcaResponseDTO valueOf(Marca marca) {
                return new MarcaResponseDTO(
                                marca.getId(),
                                marca.getNome(),
                                FornecedorResponseDTO.valueOf(marca.getFornecedor()));
        }

        public Long getId() {
                return id();
        }

}