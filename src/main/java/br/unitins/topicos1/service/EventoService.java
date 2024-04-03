package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EventoDTO;
import br.unitins.topicos1.dto.EventoResponseDTO;
import jakarta.validation.Valid;

public interface EventoService {

     public EventoResponseDTO insert(@Valid EventoDTO dto);

    public EventoResponseDTO update(@Valid EventoDTO dto, Long id);

    public void delete(Long id);

    public EventoResponseDTO findById(Long id);

    public List<EventoResponseDTO> findByDescricao(String descricao);

    public List<EventoResponseDTO> findByNome(String nome);

    public List<EventoResponseDTO> findByAll();
} 