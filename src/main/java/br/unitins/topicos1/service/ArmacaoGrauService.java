package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoGrauDTO;
import br.unitins.topicos1.dto.ArmacaoGrauResponseDTO;
import jakarta.validation.Valid;

public interface ArmacaoGrauService {
    public ArmacaoGrauResponseDTO insert(@Valid ArmacaoGrauDTO dto);

    public ArmacaoGrauResponseDTO update(@Valid ArmacaoGrauDTO dto, Long id);

    public void delete(Long id);

    public ArmacaoGrauResponseDTO findById(Long id);

    public List<ArmacaoGrauResponseDTO> findByReferencia(String referencia);

    public List<ArmacaoGrauResponseDTO> findByFabricante(String fabricante);

    public List<ArmacaoGrauResponseDTO> findByAll();

    public ArmacaoGrauResponseDTO insertNomeImagem(Long id, String nomeImagem);

    public ArmacaoGrauResponseDTO updateNomeImagem(Long id, String nomeImagem);
}
