package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    public List<Marca> findByNome(String nome) {
        return list("nome = :nome", Parameters.with("nome", nome));
    }

}
