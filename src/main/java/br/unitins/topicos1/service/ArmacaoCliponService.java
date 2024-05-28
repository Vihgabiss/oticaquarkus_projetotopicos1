package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ArmacaoCliponDTO;
import br.unitins.topicos1.dto.ArmacaoCliponResponseDTO;
import jakarta.validation.Valid;

public interface ArmacaoCliponService {
    public ArmacaoCliponResponseDTO insert(@Valid ArmacaoCliponDTO dto);

    public ArmacaoCliponResponseDTO update(@Valid ArmacaoCliponDTO dto, Long id);

    public void delete(Long id);

    public ArmacaoCliponResponseDTO findById(Long id);

    public List<ArmacaoCliponResponseDTO> findByReferencia(String referencia);

    public List<ArmacaoCliponResponseDTO> findByFabricante(String fabricante);

    public List<ArmacaoCliponResponseDTO> findByAll();

    public ArmacaoCliponResponseDTO insertNomeImagem(Long id, String nomeImagem);

    public ArmacaoCliponResponseDTO updateNomeImagem(Long id, String nomeImagem);
}
