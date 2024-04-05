package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Cidade;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade>{
    public PanacheQuery<Cidade> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)", "%"+nome+"%");
    }

    public PanacheQuery<Cidade> findAllInOrder(){
        return find("SELeCT c FROM Cidade c ORDER BY c.nome");
    }
}
