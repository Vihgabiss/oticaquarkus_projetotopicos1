package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.TipoPagamentoDTO;
import br.unitins.topicos1.dto.TipoPagamentoResponseDTO;

public interface TipoPagamentoService {

    // CREATE
    TipoPagamentoResponseDTO create(TipoPagamentoDTO tipoPagamentoDTO);

    // READ
    List<TipoPagamentoResponseDTO> getAll();

    TipoPagamentoResponseDTO getById(Long id);

    // UPDATE
    TipoPagamentoResponseDTO update(Long id, TipoPagamentoDTO tipoPagamentoDTO);

    // DELETE
    void delete(Long id);
}