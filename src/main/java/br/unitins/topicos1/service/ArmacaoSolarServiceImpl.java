package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ArmacaoSolarDTO;
import br.unitins.topicos1.dto.ArmacaoSolarResponseDTO;
import br.unitins.topicos1.model.ArmacaoSolar;
import br.unitins.topicos1.model.Colecao;
import br.unitins.topicos1.model.EstiloOculos;
import br.unitins.topicos1.model.Fabricante;
import br.unitins.topicos1.model.MaterialArmacao;
import br.unitins.topicos1.model.TipoAroArmacao;
import br.unitins.topicos1.model.TipoLenteSolar;
import br.unitins.topicos1.repository.ArmacaoSolarRepository;
import br.unitins.topicos1.repository.ColecaoRepository;
import br.unitins.topicos1.repository.EstiloOculosRepository;
import br.unitins.topicos1.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ArmacaoSolarServiceImpl implements ArmacaoSolarService {

    @Inject
    ArmacaoSolarRepository repository;

    @Inject
    FabricanteRepository fabricanteRepository;

    @Inject
    ColecaoRepository colecaoRepository;

    @Inject
    EstiloOculosRepository estiloOculosRepository;

    @Override
    @Transactional
    public ArmacaoSolarResponseDTO insert(@Valid ArmacaoSolarDTO dto) {
        ArmacaoSolar novaArmacao = new ArmacaoSolar();
        novaArmacao.setReferencia(dto.referencia());
        novaArmacao.setCor(dto.cor());
        novaArmacao.setTamanho(dto.tamanho());
        novaArmacao.setPrecoCusto(dto.precoCusto());
        novaArmacao.setPrecoVenda(dto.precoVenda());
        novaArmacao.setQuantidade(dto.quantidade());
        novaArmacao.setNomeImagem(dto.nomeImagem());
        novaArmacao.setTipoAroArmacao(TipoAroArmacao.valueOf(dto.idTipoAroArmacao()));
        novaArmacao.setMaterialArmacao(MaterialArmacao.valueOf(dto.idTipoMaterialArmacao()));

        Long idFabricante = dto.idFabricante().longValue();
        Fabricante fabricante = fabricanteRepository.findById(idFabricante);
        if (fabricante == null) {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + idFabricante);
        }
        novaArmacao.setFabricante(fabricante);

        Long idColecao = dto.idColecao().longValue();
        Colecao colecao = colecaoRepository.findById(idColecao);
        if (colecao == null) {
            throw new RuntimeException("Coleção não encontrado com o ID: " + idColecao);
        }
        novaArmacao.setColecao(colecao);

        Long idEstiloOculos = dto.idEstiloOculos().longValue();
        EstiloOculos estiloOculos = estiloOculosRepository.findById(idEstiloOculos);
        if (estiloOculos == null) {
            throw new RuntimeException("Estilo de Óculos não encontrado com o ID: " + idEstiloOculos);
        }
        novaArmacao.setEstiloOculos(estiloOculos);

        novaArmacao.setTipoLenteSolar(TipoLenteSolar.valueOf(dto.idTipoLenteSolar()));

        repository.persist(novaArmacao);

        return ArmacaoSolarResponseDTO.valueOf(novaArmacao);
    }

    @Override
    @Transactional
    public ArmacaoSolarResponseDTO update(@Valid ArmacaoSolarDTO dto, Long id) {
        ArmacaoSolar armacao = repository.findById(id);
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

        Long idFabricante = dto.idFabricante().longValue();
        Fabricante fabricante = fabricanteRepository.findById(idFabricante);
        if (fabricante == null) {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + idFabricante);
        }
        armacao.setFabricante(fabricante);

        Long idColecao = dto.idColecao().longValue();
        Colecao colecao = colecaoRepository.findById(idColecao);
        if (colecao == null) {
            throw new RuntimeException("Coleção não encontrado com o ID: " + idColecao);
        }
        armacao.setColecao(colecao);

        Long idEstiloOculos = dto.idEstiloOculos().longValue();
        EstiloOculos estiloOculos = estiloOculosRepository.findById(idEstiloOculos);
        if (estiloOculos == null) {
            throw new RuntimeException("Estilo de Óculos não encontrado com o ID: " + idEstiloOculos);
        }
        armacao.setEstiloOculos(estiloOculos);
        armacao.setTipoLenteSolar(TipoLenteSolar.valueOf(dto.idTipoLenteSolar()));

        repository.persist(armacao);

        return ArmacaoSolarResponseDTO.valueOf(armacao);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ArmacaoSolar armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armação solar não encontrada com o ID: " + id);
        }
        repository.delete(armacao);
    }

    @Override
    public ArmacaoSolarResponseDTO findById(Long id) {
        ArmacaoSolar armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armação solar não encontrada com o ID: " + id);
        }
        return ArmacaoSolarResponseDTO.valueOf(armacao);
    }

    @Override
    public List<ArmacaoSolarResponseDTO> findByReferencia(String referencia) {
        List<ArmacaoSolar> armacaoList = repository.findByReferencia(referencia);
        return armacaoList.stream()
                .map(ArmacaoSolarResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArmacaoSolarResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> ArmacaoSolarResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public ArmacaoSolarResponseDTO insertNomeImagem(Long id, String nomeImagem) {
        ArmacaoSolar armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armação Solar não encontrada com o ID: " + id);
        }

        armacao.setNomeImagem(nomeImagem);

        repository.persist(armacao);

        return ArmacaoSolarResponseDTO.valueOf(armacao);
    }

    @Override
    @Transactional
    public ArmacaoSolarResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        ArmacaoSolar armacao = repository.findById(id);
        if (armacao == null) {
            throw new RuntimeException("Armacao não encontrado com o ID: " + id);
        }

        armacao.setNomeImagem(nomeImagem);

        repository.persist(armacao);

        return ArmacaoSolarResponseDTO.valueOf(armacao);
    }

    @Override
    public List<ArmacaoSolarResponseDTO> findByFabricante(String fabricante) {
        List<ArmacaoSolar> armacaoList = repository.findByFabricante(fabricante);
        return armacaoList.stream()
                .map(ArmacaoSolarResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}