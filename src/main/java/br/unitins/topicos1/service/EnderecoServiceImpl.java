package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.CidadeRepository;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    UsuarioRepository repositoryUser;

    @Inject
    EnderecoRepository repositoryEnd;

    @Inject
    CidadeRepository repositoryCidade;

   

    @Override
    @Transactional
    public EnderecoResponseDTO insert(Long idUsuario, @Valid EnderecoDTO dto) {
        Usuario usuario = repositoryUser.findById(idUsuario);
        Cidade cidade = repositoryCidade.findById(dto.idCidade());
        

        Endereco endereco = new Endereco();
        endereco.setBairro(dto.bairro());
        endereco.setCep(dto.cep());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setIdCidade(cidade);

        usuario.getListaEndereco().add(endereco);

        repositoryEnd.persist(endereco);

        return EnderecoResponseDTO.valueOf(endereco);

    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(Long idUsuario, Long idEndereco, @Valid EnderecoDTO dto) {
        Usuario usuario = repositoryUser.findById(idUsuario);
        Endereco endereco = new Endereco();

        for (Endereco end : usuario.getListaEndereco()) {
            if (end.getId().equals(idEndereco)) {
                end.setBairro(dto.bairro());
                end.setCep(dto.cep());
                end.setRua(dto.rua());
                end.setNumero(dto.numero());
                end.setComplemento(dto.complemento());

                endereco = end;
                repositoryEnd.persist(end);

            }
        }

        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    public void delete(Long idUsuario, Long idEndereco) {
        Usuario usuario = repositoryUser.findById(idUsuario);
        Endereco endereco = new Endereco();

        for (Endereco end : usuario.getListaEndereco()){
            if(end.getId().equals(idEndereco)){
                endereco = end;
            }
        }

        usuario.getListaEndereco().remove(endereco);

        if(!repositoryEnd.deleteById(idEndereco))
            throw new NotFoundException();
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        return EnderecoResponseDTO.valueOf(repositoryEnd.findById(id));
    }

    @Override
    public List<EnderecoResponseDTO> findByCep(String cep) {
        return repositoryEnd.findByCep(cep).stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EnderecoResponseDTO> findByAll() {
       return repositoryEnd.listAll().stream()
       .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

}
