package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.TelefoneRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    TelefoneRepository repositoryTel;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) throws Exception {

        if (repository.findByEmail(dto.email()) != null) {
            throw new Exception("Esse e-mail já está sendo usado.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setCpf(dto.cpf());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(dto.senha());

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);

    }

    @Override
    @Transactional
    public UsuarioResponseDTO insertTelefone(Long idUsuario, TelefoneDTO dto) {
        Usuario usuario = repository.findById(idUsuario);

        Telefone telefone = new Telefone();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        usuario.getListaTelefone().add(telefone);

        repository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateTelefone(Long id, Long idTelefone, TelefoneDTO dto) {
        Usuario usuario = repository.findById(id);
        
            for (Telefone tel : usuario.getListaTelefone()) {
            if (tel.getId().equals(idTelefone)) {

                tel.setCodigoArea(dto.codigoArea());
                tel.setNumero(dto.numero());

                repository.persist(usuario);
            }

        }
         return UsuarioResponseDTO.valueOf(usuario);
    
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {

        Usuario novoUsuario = repository.findById(id);
        novoUsuario.setNome(dto.nome());
        novoUsuario.setSenha(dto.senha());



        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public void deleteTelefone(Long id,  Long idTelefone) {
        Usuario usuario = repository.findById(id);
        Telefone telefone =  new Telefone();


         for (Telefone tel : usuario.getListaTelefone()) {
            if (tel.getId().equals(idTelefone)) {
                telefone = tel;
            }
        }

                usuario.getListaTelefone().remove(telefone);
                
                if(!repositoryTel.deleteById(idTelefone))
                 throw new NotFoundException();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public TelefoneResponseDTO findTelById(Long id) {
        return TelefoneResponseDTO.valueOf(repositoryTel.findById(id));
    }

    @Override
    public List <TelefoneResponseDTO> findTelByCodigoArea(String codigoArea) {
        return repositoryTel.findByCodigoArea(codigoArea).stream()
                .map(e -> TelefoneResponseDTO.valueOf(e)).toList();
    }


}

