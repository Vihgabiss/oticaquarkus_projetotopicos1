package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Fabricante;

public record FabricanteResponseDTO(
        Long id,
        String nome,
        String telefone,
        String endereco,
        String email,
        String cnpj,
        MarcaResponseDTO marca

) {
    public static FabricanteResponseDTO valueOf(Fabricante fornecedor) {
        return new FabricanteResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getTelefone(),
                fornecedor.getEndereco(),
                fornecedor.getEmail(),
                fornecedor.getCnpj(),
                MarcaResponseDTO.valueOf(fornecedor.getIdMarca()));
    }
}
