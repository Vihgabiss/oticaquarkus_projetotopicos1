package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.BoletoDTO;
import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.CartaoDebitoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;
import br.unitins.topicos1.model.TipoPagamento;

public interface VendaService {

        public VendaResponseDTO insert(VendaDTO dto, String login);

        public VendaResponseDTO findById(Long id);

        public List<VendaResponseDTO> findByAll();

         public List<PagamentoResponseDTO> findByAllPagamento();

        public List<VendaResponseDTO> findByAll(String email);

        public VendaResponseDTO findItemVendaById(Long id);

        public VendaResponseDTO editStatusVenda(Long id, Integer novoStatusId);

        public VendaResponseDTO realizarPagamentoBoleto(Long vendaId, BoletoDTO boletoDTO, TipoPagamento tipoPagamento);

        public VendaResponseDTO realizarPagamentoPix(Long vendaId, PixDTO pixDTO, TipoPagamento tipoPagamento);

        public VendaResponseDTO realizarPagamentoCartaoCredito(Long vendaId, CartaoCreditoDTO cartaoCreditoDTO,
                        TipoPagamento tipoPagamento);

        public VendaResponseDTO realizarPagamentoCartaoDebito(Long vendaId, CartaoDebitoDTO cartaoDebitoDTO,
                        TipoPagamento tipoPagamento);
}