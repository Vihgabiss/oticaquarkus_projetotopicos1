package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.BoletoDTO;
import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.CartaoDebitoDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;

public interface VendaService {

        public VendaResponseDTO insert(VendaDTO dto, String login);

        public VendaResponseDTO findById(Long id);

        public List<VendaResponseDTO> findByAll();

        public List<VendaResponseDTO> findByAll(String email);

        public VendaResponseDTO findItemVendaById(Long id);

        public VendaResponseDTO editStatusVenda(Long id, Integer novoStatusId);

        public VendaResponseDTO realizarPagamentoBoleto(Long vendaId, BoletoDTO boletoDTO);

        public VendaResponseDTO realizarPagamentoPix(Long vendaId, PixDTO pixDTO);

        public VendaResponseDTO realizarPagamentoCartaoCredito(Long vendaId, CartaoCreditoDTO cartaoCreditoDTO);

        public VendaResponseDTO realizarPagamentoCartaoDebito(Long vendaId, CartaoDebitoDTO cartaoDebitoDTO);
}
