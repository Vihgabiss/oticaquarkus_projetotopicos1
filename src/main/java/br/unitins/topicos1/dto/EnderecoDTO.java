package br.unitins.topicos1.dto;


public record EnderecoDTO(
    String cep,
    String bairro,
    String rua, 
    Integer numero,
    String complemento,
    String cidade
) {
    
}
