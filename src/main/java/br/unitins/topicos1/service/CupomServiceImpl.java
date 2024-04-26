package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.CupomDTO;
import br.unitins.topicos1.dto.CupomResponseDTO;
import br.unitins.topicos1.model.Cupom;
import br.unitins.topicos1.repository.CupomRepository;
import br.unitins.topicos1.repository.EventoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CupomServiceImpl implements CupomService{

    @Inject
    CupomRepository cupomRepository;

    @Inject
    EventoRepository eventoRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public CupomResponseDTO insert(@Valid CupomDTO dto) {

        Cupom cupom = new Cupom();
        cupom.setNome(dto.nome());
        cupom.setDescricao(dto.descricao());
        cupom.setDataLancamento(dto.dataLancamento());
        cupom.setIdevento(eventoRepository.findById(dto.idEvento()));

        cupomRepository.persist(cupom);

        return CupomResponseDTO.valueOf(cupom);
    }


    @Override
    @Transactional
    public CupomResponseDTO update(Long idCupom, @Valid CupomDTO dto) {
       if (idCupom == null) {
        throw new NotFoundException();
       }

       Cupom cupom = cupomRepository.findById(idCupom);
       cupom.setNome(dto.nome());
       cupom.setDescricao(dto.descricao());
       cupom.setDataLancamento(dto.dataLancamento());
       cupom.setIdevento(eventoRepository.findById(dto.idEvento()));

       cupomRepository.persist(cupom);

       return CupomResponseDTO.valueOf(cupom);
    }

    @Override
    @Transactional
    public void delete(Long idCupom) {
        if(!cupomRepository.deleteById(idCupom))
        throw new NotFoundException();
    }

    @Override
    public List<CupomResponseDTO> findAll(int page, int pageSize) {
        List<Cupom> list = cupomRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> CupomResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public CupomResponseDTO findById(Long id) {
        return CupomResponseDTO.valueOf(cupomRepository.findById(id));
    }
    
    
}
