package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.FabricanteDTO;
import br.unitins.topicos1.dto.FabricanteResponseDTO;
import br.unitins.topicos1.model.Fabricante;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.FabricanteRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.TelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {

    @Inject
    FabricanteRepository repository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public FabricanteResponseDTO insert(FabricanteDTO dto) {
        Fabricante fabricante = new Fabricante();
        Marca marca = new Marca();
        fabricante.setNome(dto.nome());
        fabricante.setTelefone(dto.telefone());
        fabricante.setEndereco(dto.endereco());
        fabricante.setEmail(dto.email());
        fabricante.setCnpj(dto.cnpj());
        fabricante.getListaMarca().add(marca);
        repository.persist(fabricante);
        return FabricanteResponseDTO.valueOf(fabricante);
    }

    @Override
    @Transactional
    public FabricanteResponseDTO update(FabricanteDTO dto, Long id) {
        Fabricante novoFabricante = repository.findById(id);
        Marca marca = new Marca();

        if (novoFabricante == null) {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + id);
        }
        novoFabricante.setNome(dto.nome());
        novoFabricante.setTelefone(dto.telefone());
        novoFabricante.setEndereco(dto.endereco());
        novoFabricante.setEmail(dto.email());
        novoFabricante.setCnpj(dto.cnpj());
        novoFabricante.getListaMarca().add(marca);
        repository.persist(novoFabricante);
        return FabricanteResponseDTO.valueOf(novoFabricante);
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

}