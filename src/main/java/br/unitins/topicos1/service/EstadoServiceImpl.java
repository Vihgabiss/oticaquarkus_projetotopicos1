package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.CidadeResponseDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.CidadeRepository;
import br.unitins.topicos1.repository.EstadoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Override
    @Transactional
    public EstadoResponseDTO insert(@Valid EstadoDTO dto) {
        if (repository.findBySigla(dto.sigla()) != null) {
            throw new ValidationException("sigla", "Sigla já existe, verifique se este estado já está cadastrado.");
        }

        Estado novoEstado = new Estado();
        novoEstado.setNome(dto.nome());
        novoEstado.setSigla(dto.sigla());

        if (dto.listaCidade() != null && !dto.listaCidade().isEmpty()) {
            novoEstado.setListaCidade(new ArrayList<Cidade>());

            for (CidadeDTO cidade : dto.listaCidade()) {
                Cidade cid = new Cidade();
                cid.setNome(cidade.nome());
                novoEstado.getListaCidade().add(cid);

            }
        }

        repository.persist(novoEstado);

        return EstadoResponseDTO.valueOf(novoEstado);
    }

    @Override
    @Transactional
    public EstadoResponseDTO update(@Valid Long idEstado, EstadoDTO dto) {
        Estado estadoAtualizado = repository.findById(idEstado);

        estadoAtualizado.setNome(dto.nome());
        if (repository.findBySigla(dto.sigla()) != null && !estadoAtualizado.getSigla().equals(dto.sigla()))
            throw new ValidationException("sigla", "Sigla já existe, verifique se este estado já está cadastrado.");

        estadoAtualizado.setSigla(dto.sigla());

        repository.persist(estadoAtualizado);

        return EstadoResponseDTO.valueOf(estadoAtualizado);

    }

    @Override
    @Transactional
    public void delete(@Valid Long id) {
        if (!repository.deleteById(id))
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
                .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public EstadoResponseDTO findBySigla(String sigla) {
        return EstadoResponseDTO.valueOf(repository.findBySigla(sigla));

    }

    @Override
    @Transactional
    public EstadoResponseDTO insertCidade(@Valid Long idEstado, CidadeDTO dto) {
        Estado estado = repository.findById(idEstado);

        Cidade cidade = new Cidade();
        cidade.setNome(dto.nome());
        estado.getListaCidade().add(cidade);

        repository.persist(estado);

        return EstadoResponseDTO.valueOf(estado);
    }

    @Override
    @Transactional
    public EstadoResponseDTO updateCidade(@Valid Long idEstado, Long idCidade, CidadeDTO dto) {
        Estado estado = repository.findById(idEstado);

        for (Cidade cid : estado.getListaCidade()) {
            if (cid.getId().equals(idCidade)) {

                cid.setNome(dto.nome());

                repository.persist(estado);
            }
        }
        return EstadoResponseDTO.valueOf(estado);
    }

    @Override
    @Transactional
    public void deleteCidade(Long idEstado, Long idCidade) {
        Estado estado = repository.findById(idEstado);
        Cidade cidade = new Cidade();

        for (Cidade cid : estado.getListaCidade()) {
            if (cid.getId().equals(idCidade)) {
                cidade = cid;
            }
        }

        estado.getListaCidade().remove(cidade);

        if (!cidadeRepository.deleteById(idCidade))
            throw new NotFoundException();
    }

    @Override
    public List<CidadeResponseDTO> findAllCities() {
        return cidadeRepository.listAll().stream()
                .map(c -> CidadeResponseDTO.valueOf(c)).toList();
    }

}