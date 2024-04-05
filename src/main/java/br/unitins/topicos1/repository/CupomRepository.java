package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Cupom;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CupomRepository implements PanacheRepository<Cupom>{
        public PanacheQuery<Cupom> findAllInOrder(){
        return find("SELeCT c FROM Cupom c ORDER BY c.nome");
    }
}
