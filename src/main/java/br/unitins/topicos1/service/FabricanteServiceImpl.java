package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.FabricanteDTO;
import br.unitins.topicos1.dto.FabricanteResponseDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.model.Fabricante;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.FabricanteRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {

    @Inject
    FabricanteRepository repository;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public FabricanteResponseDTO insert(FabricanteDTO dto) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(dto.nome());
        fabricante.setTelefone(dto.telefone());
        fabricante.setEndereco(dto.endereco());
        fabricante.setEmail(dto.email());
        fabricante.setCnpj(dto.cnpj());

        if (dto.listaMarca() != null && !dto.listaMarca().isEmpty()) {
            fabricante.setListaMarca(new ArrayList<>());

            for (Long idMarca : dto.listaMarca()) {
                Marca marca = marcaRepository.findById(idMarca);
                if (marca != null) {
                    fabricante.getListaMarca().add(marca);
                } else {
                    // Aqui, você pode lançar uma exceção ou lidar com marcas não encontradas de
                    // outra maneira.
                    throw new RuntimeException("Marca não encontrada com o ID: " + idMarca);
                }
            }
        }

        repository.persist(fabricante);
        return FabricanteResponseDTO.valueOf(fabricante);
    }

    @Override
    @Transactional
    public FabricanteResponseDTO update(FabricanteDTO dto, Long id) {
        Fabricante fabricanteExistente = repository.findById(id);
        if (fabricanteExistente == null) {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + id);
        }

        fabricanteExistente.setNome(dto.nome());
        fabricanteExistente.setTelefone(dto.telefone());
        fabricanteExistente.setEndereco(dto.endereco());
        fabricanteExistente.setEmail(dto.email());
        fabricanteExistente.setCnpj(dto.cnpj());

        // Atualização da lista de marcas
        if (dto.listaMarca() != null && !dto.listaMarca().isEmpty()) {
            List<Marca> novasMarcas = new ArrayList<>();

            for (Long idMarca : dto.listaMarca()) {
                Marca marca = marcaRepository.findById(idMarca);
                if (marca != null) {
                    novasMarcas.add(marca);
                } else {
                    // Aqui, você pode lançar uma exceção ou lidar com marcas não encontradas de
                    // outra maneira.
                    throw new RuntimeException("Marca não encontrada com o ID: " + idMarca);
                }
            }

            fabricanteExistente.getListaMarca().clear();
            fabricanteExistente.getListaMarca().addAll(novasMarcas);
        }

        repository.persist(fabricanteExistente);
        return FabricanteResponseDTO.valueOf(fabricanteExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Fabricante fabricante = repository.findById(id);
        if (fabricante == null) {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + id);
        }
        repository.delete(fabricante);
    }

    @Override
    public FabricanteResponseDTO findById(Long id) {
        Fabricante fabricante = repository.findById(id);
        if (fabricante == null) {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + id);
        }
        return FabricanteResponseDTO.valueOf(fabricante);
    }

    @Override
    public List<FabricanteResponseDTO> findByNome(String nome) {
        List<Fabricante> fabricanteList = repository.findByNome(nome);
        return fabricanteList.stream()
                .map(FabricanteResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<FabricanteResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> FabricanteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<FabricanteResponseDTO> findByCNPJ(String cnpj) {
        List<Fabricante> fabricanteList = repository.findByCnpj(cnpj);
        if (fabricanteList.isEmpty()) {
            throw new NotFoundException("Nenhum fabricante encontrado com o CNPJ: " + cnpj);
        }
        return fabricanteList.stream()
                .map(FabricanteResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public FabricanteResponseDTO insertMarca(Long idFabricante, @Valid MarcaDTO dto) {
        Fabricante fabricante = repository.findById(idFabricante);

        Marca marca = new Marca();
        marca.setNome(dto.nome());
        fabricante.getListaMarca().add(marca);

        repository.persist(fabricante);

        return FabricanteResponseDTO.valueOf(fabricante);
    }

    @Override
    @Transactional
    public FabricanteResponseDTO updateMarca(Long id, Long idMarca, @Valid MarcaDTO dto) {
        Fabricante fabricante = repository.findById(id);
        if (fabricante == null) {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + id);
        }

        for (Marca mar : fabricante.getListaMarca()) {
            if (mar.getId().equals(idMarca)) {
                mar.setNome(dto.nome());
                repository.persist(fabricante);
                return FabricanteResponseDTO.valueOf(fabricante);
            }
        }

        throw new RuntimeException("Marca não encontrada com o ID: " + idMarca);
    }

    @Override
    public void deleteMarca(Long id, Long idMarca) {
        Fabricante fabricante = repository.findById(id);
        Marca marca = new Marca();

        for (Marca marc : fabricante.getListaMarca()) {
            if (marc.getId().equals(idMarca)) {
                marca = marc;
            }
        }

        fabricante.getListaMarca().remove(marca);

        if (!marcaRepository.deleteById(idMarca))
            throw new NotFoundException();
    }

}