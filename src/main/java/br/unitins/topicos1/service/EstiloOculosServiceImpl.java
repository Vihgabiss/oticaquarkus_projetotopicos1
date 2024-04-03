package br.unitins.topicos1.service;

import java.util.List;

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
    public List<EstiloOculosResponseDTO> findByAll() {
        return repositoryEstiloOculos.findAllInOrder().stream()
        .map(e -> EstiloOculosResponseDTO.valueOf(e)).toList();
    }
    
}