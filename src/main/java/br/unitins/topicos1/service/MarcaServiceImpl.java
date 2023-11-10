package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.model.Fornecedor;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.repository.FornecedorRepository;
import br.unitins.topicos1.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    @Inject
    MarcaRepository repository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Override
    @Transactional
    public MarcaResponseDTO insert(MarcaDTO dto) {
        Marca novaMarca = new Marca();
        novaMarca.setNome(dto.nome());

        Fornecedor fornecedorEntity = fornecedorRepository.findById(dto.fornecedor().id());
        if (fornecedorEntity == null) {
            Fornecedor novoFornecedor = new Fornecedor();
            novoFornecedor.setNome(dto.fornecedor().nome());
            novoFornecedor.setEmail(dto.fornecedor().email());
            novoFornecedor.setCnpj(dto.fornecedor().cnpj());
            novoFornecedor.setTelefone(dto.fornecedor().telefone());
            novoFornecedor.setEndereco(dto.fornecedor().endereco());
            fornecedorRepository.persist(novoFornecedor);

            novaMarca.setFornecedor(novoFornecedor);
        } else {
            novaMarca.setFornecedor(fornecedorEntity);
        }
        repository.persist(novaMarca);

        return MarcaResponseDTO.valueOf(novaMarca);
    }

    @Override
    @Transactional
    public MarcaResponseDTO update(MarcaDTO dto, Long id) {
        Marca marca = repository.findById(id);
        if (marca == null) {
            throw new RuntimeException("Marca não encontrada com o ID: " + id);
        }
        marca.setNome(dto.nome());

        Fornecedor fornecedorEntity = fornecedorRepository.findById(dto.fornecedor().id());
        if (fornecedorEntity == null) {
            Fornecedor novoFornecedor = new Fornecedor();
            novoFornecedor.setNome(dto.fornecedor().nome());
            novoFornecedor.setEmail(dto.fornecedor().email());
            novoFornecedor.setCnpj(dto.fornecedor().cnpj());
            novoFornecedor.setTelefone(dto.fornecedor().telefone());
            novoFornecedor.setEndereco(dto.fornecedor().endereco());
            fornecedorRepository.persist(novoFornecedor);

            marca.setFornecedor(novoFornecedor);
        } else {
            marca.setFornecedor(fornecedorEntity);
        }
        repository.persist(marca);

        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Marca marca = repository.findById(id);
        if (marca == null) {
            throw new RuntimeException("Marca não encontrada com o ID: " + id);
        }
        repository.delete(marca);
    }

    @Override
    public MarcaResponseDTO findById(Long id) {
        Marca marca = repository.findById(id);
        if (marca == null) {
            throw new RuntimeException("Marca não encontrada com o ID: " + id);
        }
        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    public List<MarcaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MarcaResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> MarcaResponseDTO.valueOf(e)).toList();
    }

}