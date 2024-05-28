package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoSolarDTO;
import br.unitins.topicos1.dto.ArmacaoSolarResponseDTO;
import jakarta.validation.Valid;

public interface ArmacaoSolarService {
    public ArmacaoSolarResponseDTO insert(@Valid ArmacaoSolarDTO dto);

    public ArmacaoSolarResponseDTO update(@Valid ArmacaoSolarDTO dto, Long id);

    public void delete(Long id);

    public ArmacaoSolarResponseDTO findById(Long id);

    public List<ArmacaoSolarResponseDTO> findByReferencia(String referencia);

    public List<ArmacaoSolarResponseDTO> findByFabricante(String fabricante);

    public List<ArmacaoSolarResponseDTO> findByAll();

    public ArmacaoSolarResponseDTO insertNomeImagem(Long id, String nomeImagem);

    public ArmacaoSolarResponseDTO updateNomeImagem(Long id, String nomeImagem);
}
