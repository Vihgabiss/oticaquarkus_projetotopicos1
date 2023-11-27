package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.OculosDTO;
import br.unitins.topicos1.dto.OculosResponseDTO;

public interface OculosService {
    public OculosResponseDTO insert(OculosDTO dto);

    public OculosResponseDTO update(OculosDTO dto, Long id);

    public void delete(Long id);

    public OculosResponseDTO findById(Long id);

    public List<OculosResponseDTO> findByReferencia(String referencia);

    public List<OculosResponseDTO> findByAll();

    public OculosResponseDTO insertNomeImagem(Long id, String nomeImagem);

    public OculosResponseDTO updateNomeImagem(Long id, String nomeImagem);
}