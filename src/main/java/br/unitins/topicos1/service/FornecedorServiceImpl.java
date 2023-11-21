package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;
import br.unitins.topicos1.model.Fornecedor;
import br.unitins.topicos1.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository repository;

    @Override
    @Transactional
    public FornecedorResponseDTO insert(FornecedorDTO dto) {
        Fornecedor novoFornecedor = new Fornecedor();
        novoFornecedor.setNome(dto.nome());
        novoFornecedor.setEmail(dto.email());
        novoFornecedor.setCnpj(dto.cnpj());
        novoFornecedor.setTelefone(dto.telefone());
        novoFornecedor.setEndereco(dto.endereco());

        // Save the new Fornecedor to get the generated ID
        repository.persist(novoFornecedor);

        // Return the FornecedorResponseDTO with the correct ID
        return FornecedorResponseDTO.valueOf(novoFornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(FornecedorDTO dto, Long id) {
        Fornecedor fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(dto.endereco());
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Fornecedor fornecedor = repository.findById(id);
        if (fornecedor != null) {
            repository.delete(fornecedor);
        } else {
            throw new NotFoundException("Fornecedor não encontrado para o ID: " + id);
        }
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

}