package br.unitins.topicos1.dto;

import java.util.List;

public record EstadoDTO(
    String nome,
    String sigla,
    List<CidadeDTO> listaCidade
) {

} 
