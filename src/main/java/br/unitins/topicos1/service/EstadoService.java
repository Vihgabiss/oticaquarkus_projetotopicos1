package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import jakarta.validation.Valid;

public interface EstadoService {
    public EstadoResponseDTO insert(@Valid EstadoDTO dto);

    public EstadoResponseDTO update(@Valid Long idEstado, EstadoDTO dto);

    public void delete(@Valid Long id);

    public List<EstadoResponseDTO> findByAll();

    public EstadoResponseDTO findById();

    public List<EstadoResponseDTO> findByNome(String nome);

    public EstadoResponseDTO findBySigla(String sigla);
}
