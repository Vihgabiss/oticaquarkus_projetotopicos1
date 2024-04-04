package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ArmacaoDTO;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.model.EstiloOculos;
import br.unitins.topicos1.model.Fabricante;
import br.unitins.topicos1.model.MaterialArmacao;
import br.unitins.topicos1.model.TipoAroArmacao;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.repository.EstiloOculosRepository;
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

    @Inject
    EstiloOculosRepository estiloOculosRepository;

    @Override
    @Transactional
    public ArmacaoResponseDTO insert(@Valid ArmacaoDTO dto) {
        Armacao novaArmacao = new Armacao();
        novaArmacao.setReferencia(dto.referencia());
        novaArmacao.setCor(dto.cor());
        novaArmacao.setTamanho(dto.tamanho());
        novaArmacao.setPrecoCusto(dto.precoCusto());
        novaArmacao.setPrecoVenda(dto.precoVenda());
        novaArmacao.setQuantidade(dto.quantidade());
        novaArmacao.setNomeImagem(dto.nomeImagem());
        novaArmacao.setTipoAroArmacao(TipoAroArmacao.valueOf(dto.idTipoAroArmacao()));
        novaArmacao.setMaterialArmacao(MaterialArmacao.valueOf(dto.idTipoMaterialArmacao()));

        Fabricante fabricanteEntity = fabricanteRepository.findById(dto.fabricante().id());
        if (fabricanteEntity == null) {
            throw new RuntimeException("Fabricante não encontrada com o ID: " + dto.fabricante().id());
        }
        novaArmacao.setFabricante(fabricanteEntity);

        EstiloOculos estiloOculosEntity = estiloOculosRepository.findById(dto.estiloOculos().id());
        if (estiloOculosEntity == null) {
            throw new RuntimeException("Fabricante não encontrada com o ID: " + dto.estiloOculos().id());
        }
        novaArmacao.setEstiloOculos(estiloOculosEntity);

        repository.persist(novaArmacao);

        return ArmacaoResponseDTO.valueOf(novaArmacao);
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
        armacao.setTipoAroArmacao(TipoAroArmacao.valueOf(dto.idTipoAroArmacao()));
        armacao.setMaterialArmacao(MaterialArmacao.valueOf(dto.idTipoMaterialArmacao()));
        Fabricante fabricanteEntity = fabricanteRepository.findById(dto.fabricante().id());
        if (fabricanteEntity == null) {
            throw new RuntimeException("Fabricante não encontrada com o ID: " + dto.fabricante().id());
        }
        armacao.setFabricante(fabricanteEntity);

        EstiloOculos estiloOculosEntity = estiloOculosRepository.findById(dto.estiloOculos().id());
        if (estiloOculosEntity == null) {
            throw new RuntimeException("Fabricante não encontrada com o ID: " + dto.estiloOculos().id());
        }
        armacao.setEstiloOculos(estiloOculosEntity);
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