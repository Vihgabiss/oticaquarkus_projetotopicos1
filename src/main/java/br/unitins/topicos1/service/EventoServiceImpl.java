package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.EventoDTO;
import br.unitins.topicos1.dto.EventoResponseDTO;
import br.unitins.topicos1.model.Evento;
import br.unitins.topicos1.repository.EventoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class EventoServiceImpl implements EventoService {

    @Inject
    EventoRepository repository;

    @Override
    public EventoResponseDTO insert(@Valid EventoDTO dto) {
        Evento novoEvento = new Evento();
        novoEvento.setDescricao(dto.descricao());
        novoEvento.setNome(dto.nome());

        repository.persist(novoEvento);

        return EventoResponseDTO.valueOf(novoEvento);
    }

    @Override
    public EventoResponseDTO update(@Valid EventoDTO dto, Long id) {
        Evento evento = repository.findById(id);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado com o ID: " + id);
        }

        evento.setDescricao(dto.descricao());
        evento.setNome(dto.nome());

        repository.persist(evento);

        return EventoResponseDTO.valueOf(evento);
    }

    @Override
    public void delete(Long id) {
        Evento evento = repository.findById(id);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado com o ID: " + id);
        }
        repository.delete(evento);
    }

    @Override
    public EventoResponseDTO findById(Long id) {
        Evento evento = repository.findById(id);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado com o ID: " + id);
        }
        return EventoResponseDTO.valueOf(evento);
    }

    @Override
    public List<EventoResponseDTO> findByDescricao(String descricao) {
        List<Evento> eventoList = repository.findByDescricao(descricao);
        return eventoList.stream()
                .map(EventoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventoResponseDTO> findByNome(String nome) {
        List<Evento> eventoList = repository.findByNome(nome);
        return eventoList.stream()
                .map(EventoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> EventoResponseDTO.valueOf(e)).toList();
    }

}
