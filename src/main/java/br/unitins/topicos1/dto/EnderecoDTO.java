package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;

public record EnderecoDTO(
    String cep,
    String bairro,
    String rua, 
    Integer numero,
    String complemento
) {
    
     public static EnderecoDTO valueOf(Endereco endereco){
        return new EnderecoDTO(
            endereco.getCep(),
            endereco.getBairro(),
            endereco.getRua(),
            endereco.getNumero(),
            endereco.getComplemento());
    }
}
