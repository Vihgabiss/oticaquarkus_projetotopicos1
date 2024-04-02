package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import jakarta.validation.Valid;

public interface ArmacaoService {
    public ArmacaoResponseDTO insert(@Valid ArmacaoDTO dto);

    public ArmacaoResponseDTO update(@Valid ArmacaoDTO dto, Long id);

    public void delete(Long id);

    public ArmacaoResponseDTO findById(Long id);

    public List<ArmacaoResponseDTO> findByReferencia(String referencia);

    public List<ArmacaoResponseDTO> findByFabricante(String fabricante);

    public List<ArmacaoResponseDTO> findByAll();

    public ArmacaoResponseDTO insertNomeImagem(Long id, String nomeImagem);

    public ArmacaoResponseDTO updateNomeImagem(Long id, String nomeImagem);
}
