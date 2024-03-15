package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Fornecedor;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String telefone,
        String endereco,
        String email,
        String cnpj,
        MarcaResponseDTO marca

) {
    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getTelefone(),
                fornecedor.getEndereco(),
                fornecedor.getEmail(),
                fornecedor.getCnpj(),
                MarcaResponseDTO.valueOf(fornecedor.getIdMarca()));
    }
}
