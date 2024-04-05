package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ColecaoDTO;
import br.unitins.topicos1.dto.ColecaoResponseDTO;
import jakarta.validation.Valid;

public interface ColecaoService {
    
    public ColecaoResponseDTO insert(@Valid ColecaoDTO colecaoDto);

    public ColecaoResponseDTO update(Long idColecao, @Valid ColecaoDTO colecaoDto);

    public void delete(Long idColecao);

    public ColecaoResponseDTO findById(Long idColecao);

    public List<ColecaoResponseDTO> findByAll(int page, int pageSize);

    long count();
}
