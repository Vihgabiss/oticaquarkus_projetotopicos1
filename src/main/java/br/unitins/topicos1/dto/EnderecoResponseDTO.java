package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;

public record EnderecoResponseDTO(
    String cep,
    String bairro,
    String rua, 
    Integer numero,
    String complemento
) {
    
    public static EnderecoResponseDTO valueOf(Endereco endereco){
        return new EnderecoResponseDTO(
            endereco.getCep(),
            endereco.getBairro(),
            endereco.getRua(),
            endereco.getNumero(),
            endereco.getComplemento());
    }
}
