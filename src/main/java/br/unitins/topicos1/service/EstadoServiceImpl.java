package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.EstadoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService{

    @Inject
    EstadoRepository repository;

    @Override
    @Transactional
    public EstadoResponseDTO insert(@Valid EstadoDTO dto) {
        if(repository.findBySigla(dto.sigla()) != null){
            throw new ValidationException("sigla", "Sigla já existe, verifique se este estado já está cadastrado.");
        }

        Estado novoEstado = new Estado();
        novoEstado.setNome(dto.nome());
        novoEstado.setSigla(dto.sigla());

        if (dto.listaCidade() != null && !dto.listaCidade().isEmpty()) {
            novoEstado.setListaCidade(new ArrayList<Cidade>());

            for(CidadeDTO cidade : dto.listaCidade()){
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
       if (repository.findBySigla(dto.sigla())!= null && !estadoAtualizado.getSigla().equals(dto.sigla())) 
         throw new ValidationException("sigla", "Sigla já existe, verifique se este estado já está cadastrado.");

        estadoAtualizado.setSigla(dto.sigla());

        repository.persist(estadoAtualizado);

        return EstadoResponseDTO.valueOf(estadoAtualizado);
    
    }

    @Override
    public void delete(@Valid Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public EstadoResponseDTO findBySigla(String sigla) {
        return EstadoResponseDTO.valueOf(repository.findBySigla(sigla));
               
    }

    
}
