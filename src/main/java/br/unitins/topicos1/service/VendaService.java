package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;

public interface VendaService {

    public VendaResponseDTO insert(VendaDTO dto, String login);

    public VendaResponseDTO findById(Long id);

    public List<VendaResponseDTO> findByAll();

    public List<VendaResponseDTO> findByAll(String email);

    public VendaResponseDTO findItemVendaById(Long id);

    public VendaResponseDTO editStatusVenda(Long id, Integer novoStatusId);

    public VendaResponseDTO payment(Long id, String login);
}