package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;

public interface MarcaService {
    public MarcaResponseDTO insert(MarcaDTO dto);

    public MarcaResponseDTO update(MarcaDTO dto, Long id);

    public void delete(Long id);

    public MarcaResponseDTO findById(Long id);

    public List<MarcaResponseDTO> findByNome(MarcaDTO dto);

    public List<MarcaResponseDTO> findByAll();
}
