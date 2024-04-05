package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.FabricanteDTO;
import br.unitins.topicos1.dto.FabricanteResponseDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import jakarta.validation.Valid;

public interface FabricanteService {
    public FabricanteResponseDTO insert(@Valid FabricanteDTO dto);

    public FabricanteResponseDTO update(@Valid FabricanteDTO dto, Long id);

    public FabricanteResponseDTO insertMarca(Long idFabricante, @Valid MarcaDTO dto);

    public FabricanteResponseDTO updateMarca(Long id, Long idMarca,@Valid MarcaDTO dto);

    public void deleteMarca(Long id, Long idMarca);

    public void delete(Long id);

    public FabricanteResponseDTO findById(Long id);

    public List<FabricanteResponseDTO> findByNome(String nome);

    public List<FabricanteResponseDTO> findByAll();

    public List<FabricanteResponseDTO> findByCNPJ(String cnpj);

}
