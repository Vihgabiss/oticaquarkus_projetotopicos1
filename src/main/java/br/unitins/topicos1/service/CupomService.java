package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CupomDTO;
import br.unitins.topicos1.dto.CupomResponseDTO;
import jakarta.validation.Valid;

public interface CupomService {
    
    public CupomResponseDTO insert(@Valid CupomDTO dto);

    public CupomResponseDTO update(Long idCupom, @Valid CupomDTO dto);

    public void delete(Long idCupom);

    public List<CupomResponseDTO> findAll(int page, int pageSize);

    public CupomResponseDTO findById(Long id);
}
