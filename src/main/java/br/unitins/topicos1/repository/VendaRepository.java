package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Venda;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VendaRepository implements PanacheRepository<Venda> {
    public List<Venda> findAll(String login) {
        return find("usuario.login = ?1", login).list();
    }

    public List<Venda> findAll(Long idUsuario) {
        return find("usuario.id = ?1", idUsuario).list();
    }
}
