package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.TipoPagamentoDTO;
import br.unitins.topicos1.dto.TipoPagamentoResponseDTO;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.repository.TipoPagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TipoPagamentoServiceImpl implements TipoPagamentoService {

    @Inject
    TipoPagamentoRepository tipoPagamentoRepository;

    // CREATE
    @Transactional
    public TipoPagamentoResponseDTO create(TipoPagamentoDTO tipoPagamentoDTO) {
        TipoPagamento entity = new TipoPagamento();
        entity.setNome(tipoPagamentoDTO.nome());
        tipoPagamentoRepository.persist(entity);
        return TipoPagamentoResponseDTO.valueOf(entity);
    }

    // READ
    public List<TipoPagamentoResponseDTO> getAll() {
        return tipoPagamentoRepository.listAll()
                .stream()
                .map(TipoPagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    public TipoPagamentoResponseDTO getById(Long id) {
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(id);
        if (tipoPagamento == null) {
            throw new NotFoundException("Tipo de pagamento não encontrado.");
        }
        return TipoPagamentoResponseDTO.valueOf(tipoPagamento);
    }

    // UPDATE
    @Transactional
    public TipoPagamentoResponseDTO update(Long id, TipoPagamentoDTO tipoPagamentoDTO) {
        TipoPagamento entity = tipoPagamentoRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Tipo de pagamento não encontrado.");
        }
        entity.setNome(tipoPagamentoDTO.nome());
        return TipoPagamentoResponseDTO.valueOf(entity);
    }

    // DELETE
    @Transactional
    public void delete(Long id) {
        tipoPagamentoRepository.deleteById(id);
    }
}
