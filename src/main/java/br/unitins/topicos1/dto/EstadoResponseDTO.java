package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Estado;

public record EstadoResponseDTO(
        String nome,
        String sigla,
        List<CidadeResponseDTO> listaCidade) {

    public static EstadoResponseDTO valueOf(Estado estado) {
        return new EstadoResponseDTO(
                estado.getNome(),
                estado.getSigla(),
                estado.getListaCidade()
                        .stream()
                        .map(c -> CidadeResponseDTO.valueOf(c)).toList());
    }
}