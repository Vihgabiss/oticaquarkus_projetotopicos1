package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;

public interface FornecedorService {

    public FornecedorResponseDTO insert(FornecedorDTO dto);

    public FornecedorResponseDTO update(FornecedorDTO dto, Long id);

    public void delete(Long id);

    public FornecedorResponseDTO findById(Long id);

    public List<FornecedorResponseDTO> findByNome(String nome);

    public List<FornecedorResponseDTO> findByAll();
}
