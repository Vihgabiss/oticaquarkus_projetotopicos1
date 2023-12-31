package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.model.Endereco;

public record EnderecoResponseDTO(
    Long id,
    String cep,
    String bairro,
    String rua, 
    Integer numero,
    String complemento,
    Cidade idCidade
) {
         public static EnderecoResponseDTO valueOf(Endereco endereco){
        return new EnderecoResponseDTO(
            endereco.getId(),
            endereco.getCep(),
            endereco.getBairro(),
            endereco.getRua(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getIdCidade());
  
    }
}
