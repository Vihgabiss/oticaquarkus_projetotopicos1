package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.OculosDTO;
import br.unitins.topicos1.dto.OculosResponseDTO;
import br.unitins.topicos1.model.Marca;
import br.unitins.topicos1.model.Oculos;
import br.unitins.topicos1.model.TipoOculos;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.OculosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class OculosServiceImpl implements OculosService {

    @Inject
    OculosRepository repository;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public OculosResponseDTO insert(@Valid OculosDTO dto) {
        Oculos novoOculos = new Oculos();
        novoOculos.setReferencia(dto.referencia());
        novoOculos.setCor(dto.cor());
        novoOculos.setTamanho(dto.tamanho());
        novoOculos.setPrecoCusto(dto.precoCusto());
        novoOculos.setPrecoVenda(dto.precoVenda());
        novoOculos.setQuantidade(dto.quantidade());
        novoOculos.setNomeImagem(dto.nomeImagem());

        Marca marcaEntity = marcaRepository.findById(dto.marca().getId());
        if (marcaEntity == null) {
            throw new RuntimeException("Marca não encontrada com o ID: " + dto.marca().getId());
        }
        novoOculos.setMarca(marcaEntity);

        novoOculos.setTipoOculos(TipoOculos.valueOf(dto.idTipoOculos()));
        repository.persist(novoOculos);

        return OculosResponseDTO.valueOf(novoOculos);
    }

    @Override
    @Transactional
    public OculosResponseDTO update(@Valid OculosDTO dto, Long id) {
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
        oculos.setNomeImagem(dto.nomeImagem());
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

    @Override
    @Transactional
    public OculosResponseDTO insertNomeImagem(Long id, String nomeImagem) {
        Oculos oculos = repository.findById(id);
        if (oculos == null) {
            throw new RuntimeException("Oculos não encontrado com o ID: " + id);
        }

        oculos.setNomeImagem(nomeImagem);

        repository.persist(oculos);

        return OculosResponseDTO.valueOf(oculos);
    }

    @Override
    @Transactional
    public OculosResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Oculos oculos = repository.findById(id);
        if (oculos == null) {
            throw new RuntimeException("Oculos não encontrado com o ID: " + id);
        }

        oculos.setNomeImagem(nomeImagem);

        repository.persist(oculos);

        return OculosResponseDTO.valueOf(oculos);
    }

    @Override
    public List<OculosResponseDTO> findByMarca(String marca) {
        List<Oculos> oculosList = repository.findByMarca(marca);
        return oculosList.stream()
                .map(OculosResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<OculosResponseDTO> findByTipoOculos(Integer idTipoOculos) {
        TipoOculos tipoOculos = TipoOculos.valueOf(idTipoOculos);
        List<Oculos> oculosList = repository.findByTipOculos(tipoOculos);
        return oculosList.stream()
                .map(OculosResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}