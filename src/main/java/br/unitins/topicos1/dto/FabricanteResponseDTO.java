package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Fabricante;

public record FabricanteResponseDTO(
        Long id,
        String nome,
        String telefone,
        String endereco,
        String email,
        String cnpj,
        List<MarcaResponseDTO> listaMarca

) {
    public static FabricanteResponseDTO valueOf(Fabricante fabricante) {
        return new FabricanteResponseDTO(
                fabricante.getId(),
                fabricante.getNome(),
                fabricante.getTelefone(),
                fabricante.getEndereco(),
                fabricante.getEmail(),
                fabricante.getCnpj(),
                fabricante.getListaMarca().stream().map(t -> MarcaResponseDTO.valueOf(t)).toList());
    }
}
