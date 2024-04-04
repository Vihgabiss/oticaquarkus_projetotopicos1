package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ColecaoDTO;
import br.unitins.topicos1.dto.ColecaoResponseDTO;
import br.unitins.topicos1.model.Colecao;
import br.unitins.topicos1.repository.ColecaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ColecaoServiceImpl implements ColecaoService {

    @Inject
    ColecaoRepository repositoryColecao;

    @Override
    public ColecaoResponseDTO insert(@Valid ColecaoDTO colecaoDto) {
        
        Colecao colecao = new Colecao();
        colecao.setNome(colecaoDto.nome());
        colecao.setDescricao(colecaoDto.descricao());
        colecao.setDataLancamento(colecaoDto.dataLancamento());

        repositoryColecao.persist(colecao);

        return ColecaoResponseDTO.valueOf(colecao);
    }

    @Override
    public ColecaoResponseDTO update(Long idColecao, @Valid ColecaoDTO colecaoDto) {
        Colecao colecao = repositoryColecao.findById(idColecao);

        colecao.setNome(colecaoDto.nome());
        colecao.setDescricao(colecaoDto.descricao());
        colecao.setDataLancamento(colecaoDto.dataLancamento());

        repositoryColecao.persist(colecao);

        return ColecaoResponseDTO.valueOf(colecao);
    }

    @Override
    public void delete(Long idColecao) {
        
        if(!repositoryColecao.deleteById(idColecao))
            throw new NotFoundException();
    }

    @Override
    public ColecaoResponseDTO findById(Long idColecao) {
        return ColecaoResponseDTO.valueOf(repositoryColecao.findById(idColecao));
    }

    @Override
    public List<ColecaoResponseDTO> findByAll() {
        return repositoryColecao.findAllInOrder().stream()
        .map(e -> ColecaoResponseDTO.valueOf(e)).toList();
    }
    
}
