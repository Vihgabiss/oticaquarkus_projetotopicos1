package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.OculosDTO;
import br.unitins.topicos1.dto.OculosResponseDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Oculos;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.OculosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OculosServiceImpl implements OculosService {

    @Inject
    OculosRepository repository;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public OculosResponseDTO insert(OculosDTO dto) {
        Oculos novoOculos = new Oculos();
        novoOculos.setReferencia(dto.referencia());
        novoOculos.setCor(dto.cor());
        novoOculos.setTamanho(dto.tamanho());
        novoOculos.setPrecoCusto(dto.precoCusto());
        novoOculos.setPrecoVenda(dto.precoVenda());
        novoOculos.setQuantidade(dto.quantidade());

        Marca marcaEntity = marcaRepository.findById(dto.marca().getId());
        if (marcaEntity == null) {
            throw new RuntimeException("Marca não encontrada com o ID: " + dto.marca().getId());
        }
        novoOculos.setMarca(marcaEntity);

        repository.persist(novoOculos);

        return OculosResponseDTO.valueOf(novoOculos);
    }

    @Override
    @Transactional
    public OculosResponseDTO update(OculosDTO dto, Long id) {
        Oculos oculos = repository.findById(id);
        if (oculos == null) {
            throw new RuntimeException("Oculos não encontrado com o ID: " + id);
        }
        oculos.setReferencia(dto.referencia());
        oculos.setCor(dto.cor());
        oculos.setTamanho(dto.tamanho());
        oculos.setPrecoCusto(dto.precoCusto());
        oculos.setPrecoVenda(dto.precoVenda());
        oculos.setQuantidade(dto.quantidade());
        Marca marcaEntity = marcaRepository.findById(dto.marca().getId());
        if (marcaEntity == null) {
            throw new RuntimeException("Marca não encontrada com o ID: " + dto.marca().getId());
        }
        oculos.setMarca(marcaEntity);

        repository.persist(oculos);

        return OculosResponseDTO.valueOf(oculos);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Oculos oculos = repository.findById(id);
        if (oculos == null) {
            throw new RuntimeException("Oculos não encontrado com o ID: " + id);
        }
        repository.delete(oculos);
    }

    @Override
    public OculosResponseDTO findById(Long id) {
        Oculos oculos = repository.findById(id);
        if (oculos == null) {
            throw new RuntimeException("Oculos não encontrado com o ID: " + id);
        }
        return OculosResponseDTO.valueOf(oculos);
    }

    @Override
    public List<OculosResponseDTO> findByReferencia(String referencia) {
        List<Oculos> oculosList = repository.findByReferencia(referencia);
        return oculosList.stream()
                .map(OculosResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<OculosResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> OculosResponseDTO.valueOf(e)).toList();
    }
}
