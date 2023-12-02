package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;
import jakarta.validation.Valid;

public interface FornecedorService {
    public FornecedorResponseDTO insert(@Valid FornecedorDTO dto);

    public FornecedorResponseDTO update(@Valid FornecedorDTO dto, Long id);

    public void delete(Long id);

    public FornecedorResponseDTO findById(Long id);

    public List<FornecedorResponseDTO> findByNome(String nome);

    public List<FornecedorResponseDTO> findByAll();

    public List<FornecedorResponseDTO> findByCNPJ(String cnpj);

}
