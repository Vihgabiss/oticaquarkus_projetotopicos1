package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Colecao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ColecaoRepository implements PanacheRepository<Colecao>{


        public PanacheQuery<Colecao> findAllInOrder(){
        return find("SELeCT c FROM Colecao c ORDER BY c.nome");
    }
}
