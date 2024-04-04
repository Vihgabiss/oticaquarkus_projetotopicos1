package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Evento;

public record EventoResponseDTO(
        Long id,
        String descricao,
        String nome) {
    public static EventoResponseDTO valueOf(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getDescricao(),
                evento.getNome());
    }
}
