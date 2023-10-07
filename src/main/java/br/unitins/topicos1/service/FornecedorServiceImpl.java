package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Fornecedor;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.FornecedorRepository;
import br.unitins.topicos1.repository.TelefoneRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository repository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public FornecedorResponseDTO insert(FornecedorDTO dto) {
        Fornecedor novoFornecedor = new Fornecedor();
        novoFornecedor.setNome(dto.getNome());
        novoFornecedor.setEmail(dto.getEmail());
        novoFornecedor.setCnpj(dto.getCnpj());
        Telefone telefoneEntity = telefoneRepository.findById(dto.getTelefone().getId());
        if (telefoneEntity == null) {
            throw new RuntimeException("Telefone não encontrado com o ID: " + dto.getTelefone().getId());
        }
        novoFornecedor.setTelefone(telefoneEntity);

        Endereco enderecoEntity = enderecoRepository.findById(dto.getEndereco().getId());
        if (enderecoEntity == null) {
            throw new RuntimeException("Endereço não encontrado com o ID: " + dto.getEndereco().getId());
        }
        novoFornecedor.setEndereco(enderecoEntity);

        repository.persist(novoFornecedor);

        return FornecedorResponseDTO.valueOf(novoFornecedor);

    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(FornecedorDTO dto, Long id) {
        Fornecedor fornecedor = repository.findById(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        fornecedor.setNome(dto.getNome());
        fornecedor.setCnpj(dto.getCnpj());
        fornecedor.setEmail(dto.getEmail());
        Telefone telefoneEntity = telefoneRepository.findById(dto.getTelefone().getId());
        if (telefoneEntity == null) {
            throw new RuntimeException("Telefone não encontrado com o ID: " + dto.getTelefone().getId());
        }
        fornecedor.setTelefone(telefoneEntity);

        Endereco enderecoEntity = enderecoRepository.findById(dto.getEndereco().getId());
        if (enderecoEntity == null) {
            throw new RuntimeException("Endereco não encontrado com o ID: " + dto.getTelefone().getId());
        }
        fornecedor.setEndereco(enderecoEntity);
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
            throw new RuntimeException("Oculos não encontrado com o ID: " + id);
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