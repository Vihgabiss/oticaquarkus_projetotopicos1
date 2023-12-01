package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import jakarta.validation.Valid;

public interface MarcaService {
    public MarcaResponseDTO insert(@Valid MarcaDTO dto);

    public MarcaResponseDTO update(@Valid MarcaDTO dto, Long id);

    public void delete(Long id);

    public MarcaResponseDTO findById(Long id);

    public List<MarcaResponseDTO> findByNome(String nome);

    public List<MarcaResponseDTO> findByAll();
}
