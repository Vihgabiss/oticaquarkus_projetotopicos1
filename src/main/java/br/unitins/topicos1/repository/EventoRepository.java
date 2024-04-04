package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Evento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventoRepository implements PanacheRepository<Evento> {
    public List<Evento> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1) ", "%" + descricao + "%").list();
    }

    public List<Evento> findByNome(String nome) {
        return find("UPPER(nome)  LIKE UPPER(?1)", "%" + nome + "%").list();
    }
}
