package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.CidadeResponseDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import jakarta.validation.Valid;

public interface EstadoService {
    public EstadoResponseDTO insert(@Valid EstadoDTO dto);

    public EstadoResponseDTO update(Long idEstado, @Valid EstadoDTO dto);

    public void delete(Long id);

    public List<EstadoResponseDTO> findByAll(int page, int pageSize);

    public EstadoResponseDTO findById(Long id);

    public List<EstadoResponseDTO> findByNome(String nome);

    public EstadoResponseDTO findBySigla(String sigla);

    public CidadeResponseDTO insertCidade(@Valid CidadeDTO dto);

    public CidadeResponseDTO updateCidade(Long idCidade, @Valid CidadeDTO dto);

    public void deleteCidade(Long idCidade);

    public List<CidadeResponseDTO> findAllCities(int page, int pageSize);

    public CidadeResponseDTO findCidadeById(Long id);

    long count();

    long countCidade();
}
