package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Fornecedor;
import br.unitins.topicos1.model.Telefone;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        Telefone telefone,
        String email,
        Endereco endereco,
        String cnpj) {
    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getEndereco(),
                fornecedor.getCnpj());
    }
}
