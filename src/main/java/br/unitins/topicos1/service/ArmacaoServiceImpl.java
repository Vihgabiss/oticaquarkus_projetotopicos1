package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.Fabricante;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ArmacaoServiceImpl implements ArmacaoService {

    @Inject
    ArmacaoRepository repository;

    @Inject
    FabricanteRepository fabricanteRepository;

    @Override
    @Transactional
    public ArmacaoResponseDTO insert(@Valid ArmacaoDTO dto) {
        Armacao novoArmacao = new Armacao();
        novoArmacao.setReferencia(dto.referencia());
        novoArmacao.setCor(dto.cor());
        novoArmacao.setTamanho(dto.tamanho());
        novoArmacao.setPrecoCusto(dto.precoCusto());
        novoArmacao.setPrecoVenda(dto.precoVenda());
        novoArmacao.setQuantidade(dto.quantidade());
        novoArmacao.setNomeImagem(dto.nomeImagem());

        Fabricante fabricanteEntity = fabricanteRepository.findById(dto.fabricante().id());
        if (fabricanteEntity == null) {
            throw new RuntimeException("Fabricante não encontrada com o ID: " + dto.fabricante().id());
        }
        novoArmacao.setFabricante(fabricanteEntity);

        repository.persist(novoArmacao);

        return ArmacaoResponseDTO.valueOf(novoArmacao);
    }

    @Override
    @Transactional
    public ArmacaoResponseDTO update(@Valid ArmacaoDTO dto, Long id) {
        Armacao armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armacao não encontrado com o ID: " + id);
        }
        armacao.setReferencia(dto.referencia());
        armacao.setCor(dto.cor());
        armacao.setTamanho(dto.tamanho());
        armacao.setPrecoCusto(dto.precoCusto());
        armacao.setPrecoVenda(dto.precoVenda());
        armacao.setQuantidade(dto.quantidade());
        armacao.setNomeImagem(dto.nomeImagem());
        Fabricante fabricanteEntity = fabricanteRepository.findById(dto.fabricante().id());
        if (fabricanteEntity == null) {
            throw new RuntimeException("Fabricante não encontrada com o ID: " + dto.fabricante().id());
        }
        armacao.setFabricante(fabricanteEntity);

        repository.persist(armacao);

        return ArmacaoResponseDTO.valueOf(armacao);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Armacao armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armacao não encontrado com o ID: " + id);
        }
        repository.delete(armacao);
    }

    @Override
    public ArmacaoResponseDTO findById(Long id) {
        Armacao armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armacao não encontrado com o ID: " + id);
        }
        return ArmacaoResponseDTO.valueOf(armacao);
    }

    @Override
    public List<ArmacaoResponseDTO> findByReferencia(String referencia) {
        List<Armacao> armacaoList = repository.findByReferencia(referencia);
        return armacaoList.stream()
                .map(ArmacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArmacaoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> ArmacaoResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public ArmacaoResponseDTO insertNomeImagem(Long id, String nomeImagem) {
        Armacao armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armacao não encontrado com o ID: " + id);
        }

        armacao.setNomeImagem(nomeImagem);

        repository.persist(armacao);

        return ArmacaoResponseDTO.valueOf(armacao);
    }

    @Override
    @Transactional
    public ArmacaoResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Armacao armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armacao não encontrado com o ID: " + id);
        }

        armacao.setNomeImagem(nomeImagem);

        repository.persist(armacao);

        return ArmacaoResponseDTO.valueOf(armacao);
    }

    @Override
    public List<ArmacaoResponseDTO> findByFabricante(String fabricante) {
        List<Armacao> armacaoList = repository.findByFabricante(fabricante);
        return armacaoList.stream()
                .map(ArmacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}