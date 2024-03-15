package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;
import br.unitins.topicos1.model.Fornecedor;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.FornecedorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.TelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository repository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public FornecedorResponseDTO insert(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(dto.endereco());
        fornecedor.setEmail(dto.email());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setIdMarca(marcaRepository.findById(dto.idMarca()));
        repository.persist(fornecedor);
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(FornecedorDTO dto, Long id) {
        Fornecedor fornecedor = repository.findById(id);
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
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Fornecedor fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        repository.delete(fornecedor);
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome) {
        List<Fornecedor> fornecedorList = repository.findByNome(nome);
        return fornecedorList.stream()
                .map(FornecedorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<FornecedorResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> FornecedorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<FornecedorResponseDTO> findByCNPJ(String cnpj) {
        List<Fornecedor> fornecedorList = repository.findByCnpj(cnpj);
        if (fornecedorList.isEmpty()) {
            throw new NotFoundException("Nenhum fornecedor encontrado com o CNPJ: " + cnpj);
        }
        return fornecedorList.stream()
                .map(FornecedorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}
