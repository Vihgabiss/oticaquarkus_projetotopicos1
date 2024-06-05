package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EstiloOculosDTO;
import br.unitins.topicos1.dto.EstiloOculosResponseDTO;
import jakarta.validation.Valid;

public interface EstiloOculosService {

    public EstiloOculosResponseDTO insert(@Valid EstiloOculosDTO estiloOculosDto);

    public EstiloOculosResponseDTO update(Long idEstiloOculos, @Valid EstiloOculosDTO estiloOculosDto);

    public void delete(Long idEstiloOculos);

    public EstiloOculosResponseDTO findById(Long idEstiloOculos);

    public List<EstiloOculosResponseDTO> findByIdList(Long idEstiloOculos, int page, int pageSize);

    public List<EstiloOculosResponseDTO> findByAll(int page, int pageSize);

    public List<EstiloOculosResponseDTO> findByNome(String nome);

    public List<EstiloOculosResponseDTO> findByDescricao(String descricao);

    public List<EstiloOculosResponseDTO> findInAllFields(String termo);

    long count();

    public long countById(Long id);

}
