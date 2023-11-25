package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.CidadeResponseDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import jakarta.validation.Valid;

public interface EstadoService {
    public EstadoResponseDTO insert(@Valid EstadoDTO dto);

    public EstadoResponseDTO update(@Valid Long idEstado, EstadoDTO dto);

    public void delete(@Valid Long id);

    public List<EstadoResponseDTO> findByAll();

    public EstadoResponseDTO findById(Long id);

    public List<EstadoResponseDTO> findByNome(String nome);

    public EstadoResponseDTO findBySigla(String sigla);

    public EstadoResponseDTO insertCidade(@Valid Long idEstado, CidadeDTO dto);

    public EstadoResponseDTO updateCidade(@Valid Long idEstado, Long idCidade, CidadeDTO dto);

    public void deleteCidade(Long idEstado, Long idCidade);

    public List<CidadeResponseDTO> findAllCities();
}