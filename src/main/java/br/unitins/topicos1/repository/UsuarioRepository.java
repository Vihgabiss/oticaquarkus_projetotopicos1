package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public PanacheQuery<Usuario>findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

    public Usuario findByEmail(String email) {
        try {
            return find("email = ?1", email).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario findByEmailAndSenha(String email, String senha) {
        try {
            return find("email = ?1 AND senha = ?2", email, senha).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
