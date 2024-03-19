package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.CidadeResponseDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.CidadeRepository;
import br.unitins.topicos1.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService{

    @Inject
    EstadoRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public EstadoResponseDTO insert(@Valid EstadoDTO dto) {

        Estado novoEstado = new Estado();
        novoEstado.setNome(dto.nome());
        novoEstado.setSigla(dto.sigla());

        repository.persist(novoEstado);

        return EstadoResponseDTO.valueOf(novoEstado);
    }

    @Override
    @Transactional
    public EstadoResponseDTO update(Long idEstado, @Valid EstadoDTO dto) {
       Estado estadoAtualizado = repository.findById(idEstado);

       estadoAtualizado.setNome(dto.nome());
       estadoAtualizado.setSigla(dto.sigla());

        repository.persist(estadoAtualizado);

        return EstadoResponseDTO.valueOf(estadoAtualizado);
    
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!repository.deleteById(id))
        throw new NotFoundException(); 
    }

    @Override
    public List<EstadoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        return EstadoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome) {
       return repository.findByNome(nome).stream()
                .map(e-> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public EstadoResponseDTO findBySigla(String sigla) {
        return EstadoResponseDTO.valueOf(repository.findBySigla(sigla));
               
    }

    private void validar(CidadeDTO dto) throws ConstraintViolationException{
        Set<ConstraintViolation<CidadeDTO>> violations = validator.validate(dto);
        if(!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public CidadeResponseDTO insertCidade(@Valid CidadeDTO dto) {
        // Estado estado = repository.findById(dto.idEstado());

        // Cidade cidade = new Cidade();
        // cidade.setNome(dto.nome());
        // cidade.setIdEstado(estado);

        // cidadeRepository.persist(cidade);

        // return CidadeResponseDTO.valueOf(cidade);

        validar(dto);

        Cidade entity = new Cidade();
        entity.setNome(dto.nome());
        entity.setIdEstado(repository.findById(dto.idEstado()));

        cidadeRepository.persist(entity);
        return CidadeResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CidadeResponseDTO updateCidade(Long idCidade, @Valid CidadeDTO dto) {
        if(idCidade == null)
            throw new NotFoundException();

        Cidade cidade = cidadeRepository.findById(idCidade);
                cidade.setNome(dto.nome());

                cidadeRepository.persist(cidade);
        
         
         return CidadeResponseDTO.valueOf(cidade);
    }

    @Override
    @Transactional
    public void deleteCidade(Long idCidade) {

        if(!cidadeRepository.deleteById(idCidade))
            throw new NotFoundException();
    }

    @Override
    public List<CidadeResponseDTO> findAllCities() {
       return cidadeRepository.listAll().stream()
            .map(c -> CidadeResponseDTO.valueOf(c)).toList();
    }

    
}
