package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.FabricanteDTO;
import br.unitins.topicos1.dto.FabricanteResponseDTO;
import br.unitins.topicos1.model.Fabricante;
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
        Fabricante fornecedor = new Fabricante();
        fornecedor.setNome(dto.nome());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(dto.endereco());
        fornecedor.setEmail(dto.email());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setIdMarca(marcaRepository.findById(dto.idMarca()));
        repository.persist(fornecedor);
        return FabricanteResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FabricanteResponseDTO update(FabricanteDTO dto, Long id) {
        Fabricante fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        fornecedor.setNome(dto.nome());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(dto.endereco());
        fornecedor.setEmail(dto.email());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setIdMarca(marcaRepository.findById(dto.idMarca()));
        repository.persist(fornecedor);
        return FabricanteResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Fabricante fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        repository.delete(fornecedor);
    }

    @Override
    public FabricanteResponseDTO findById(Long id) {
        Fabricante fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        return FabricanteResponseDTO.valueOf(fornecedor);
    }

    @Override
    public List<FabricanteResponseDTO> findByNome(String nome) {
        List<Fabricante> fornecedorList = repository.findByNome(nome);
        return fornecedorList.stream()
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
        List<Fabricante> fornecedorList = repository.findByCnpj(cnpj);
        if (fornecedorList.isEmpty()) {
            throw new NotFoundException("Nenhum fornecedor encontrado com o CNPJ: " + cnpj);
        }
        return fornecedorList.stream()
                .map(FabricanteResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}
