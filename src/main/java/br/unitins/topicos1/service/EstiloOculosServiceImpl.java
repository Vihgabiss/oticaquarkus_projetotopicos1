package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.EstiloOculosDTO;
import br.unitins.topicos1.dto.EstiloOculosResponseDTO;
import br.unitins.topicos1.model.EstiloOculos;
import br.unitins.topicos1.repository.EstiloOculosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstiloOculosServiceImpl implements EstiloOculosService{

    @Inject
    EstiloOculosRepository repositoryEstiloOculos;

    @Override
    public EstiloOculosResponseDTO insert(@Valid EstiloOculosDTO estiloOculosDto) {
        
        EstiloOculos estiloOculos = new EstiloOculos();
        estiloOculos.setNome(estiloOculosDto.nome());
        estiloOculos.setDescricao(estiloOculosDto.descricao());

        repositoryEstiloOculos.persist(estiloOculos);

        return EstiloOculosResponseDTO.valueOf(estiloOculos);
    }

    @Override
    public EstiloOculosResponseDTO update(Long idEstiloOculos, @Valid EstiloOculosDTO estiloOculosDto) {
        EstiloOculos estiloOculos = repositoryEstiloOculos.findById(idEstiloOculos);

        estiloOculos.setNome(estiloOculosDto.nome());
        estiloOculos.setDescricao(estiloOculosDto.descricao());

        repositoryEstiloOculos.persist(estiloOculos);

        return EstiloOculosResponseDTO.valueOf(estiloOculos);
    }

    @Override
    public void delete(Long idEstiloOculos) {

        if(!repositoryEstiloOculos.deleteById(idEstiloOculos))
        throw new NotFoundException();
    }

    @Override
    public EstiloOculosResponseDTO findById(Long idEstiloOculos) {
        return EstiloOculosResponseDTO.valueOf(repositoryEstiloOculos.findById(idEstiloOculos));
    }

    @Override
    public List<EstiloOculosResponseDTO> findByIdList(Long idEstiloOculos, int page, int pageSize) {
        List<EstiloOculos> list = repositoryEstiloOculos.findByIdList(idEstiloOculos).page(page, pageSize).list(); 
        return list.stream().map(e -> EstiloOculosResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public List<EstiloOculosResponseDTO> findByAll(int page, int pageSize) {
        List<EstiloOculos> list = repositoryEstiloOculos.findAll().page(page, pageSize).list();
        return list.stream().map(e -> EstiloOculosResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public List<EstiloOculosResponseDTO> findByNome(String nome) {
        return repositoryEstiloOculos.findByNome(nome).stream()
        .map(e -> EstiloOculosResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstiloOculosResponseDTO> findByDescricao(String descricao) {
        return repositoryEstiloOculos.findByDescricao(descricao).stream()
        .map(e -> EstiloOculosResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstiloOculosResponseDTO> findInAllFields(String termo) {
        return repositoryEstiloOculos.findInAllFields(termo).stream()
        .map(e -> EstiloOculosResponseDTO.valueOf(e)).toList();
    }

    @Override
    public long count() {
       return repositoryEstiloOculos.count();
    }

    @Override
    public long countById(Long id){
        return repositoryEstiloOculos.findByIdList(id).count();
    }
    
}
