package br.unitins.topicos1.service;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.dto.SenhaDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.TelefoneRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    TelefoneRepository repositoryTel;

    @Inject
    HashService hashService;

     @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {

        if (repository.findByEmail(dto.email()) != null) {
            throw new ValidationException("email", "Email já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setCpf(dto.cpf());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        novoUsuario.setPerfil(Perfil.valueOf(dto.idPerfil()));

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);

    }

    @Override
    @Transactional
    public UsuarioResponseDTO insertTelefone(Long idUsuario, @Valid TelefoneDTO dto) {
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
    public UsuarioResponseDTO updateTelefone(Long id, Long idTelefone, @Valid TelefoneDTO dto) {
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
    public UsuarioResponseDTO update(@Valid UsuarioDTO dto, Long id) {

        Usuario novoUsuario = repository.findById(id);
        novoUsuario.setNome(dto.nome());
        novoUsuario.setSenha(dto.senha());

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public void deleteTelefone(Long id, Long idTelefone) {
        Usuario usuario = repository.findById(id);
        Telefone telefone = new Telefone();

        for (Telefone tel : usuario.getListaTelefone()) {
            if (tel.getId().equals(idTelefone)) {
                telefone = tel;
            }
        }

        usuario.getListaTelefone().remove(telefone);

        if (!repositoryTel.deleteById(idTelefone))
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
    public List<TelefoneResponseDTO> findTelByCodigoArea(String codigoArea) {
        return repositoryTel.findByCodigoArea(codigoArea).stream()
                .map(e -> TelefoneResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByEmailAndSenha(String email, String senha) {
        Usuario usuario = repository.findByEmailAndSenha(email, senha);
        if (usuario == null)
            throw new ValidationException("login", "E-mail ou senha inválido");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null)
            throw new ValidationException("email", "E-mail inválido");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    public Usuario getUsuarioByEmail(){
        String email = jwt.getSubject();
        Usuario usuario = usuarioRepository.findByEmail(email);

        return usuario;
    }

    @Override
    @Transactional
    public void updateSenha(@Valid SenhaDTO dto){
        Usuario usuario = getUsuarioByEmail();
        String senhaAtualHash = hashService.getHashSenha(dto.senhaAtual());

        if (usuario.getSenha().equals(senhaAtualHash)){
                usuario.setSenha(hashService.getHashSenha(dto.senhaNova()));     
        } 

        else
            throw new ValidationException("senha", "Senha errada!");
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateNomeUsuarioLogado(String nome){
        Usuario usuario = getUsuarioByEmail();

        if (nome != null && !nome.isEmpty())
            usuario.setNome(nome);
        else
        throw new ValidationException("nome", "Nome não pode ser nulo.");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insertTelefoneUsuarioLogado(@Valid TelefoneDTO dto){
        Usuario usuario = getUsuarioByEmail(); 
        return insertTelefone(usuario.getId(), dto);   
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateTelefoneUsuarioLogado(Long idTelefone, @Valid TelefoneDTO dto){
        Usuario usuario = getUsuarioByEmail(); 
        return updateTelefone(usuario.getId(), idTelefone, dto);  
    }
}
