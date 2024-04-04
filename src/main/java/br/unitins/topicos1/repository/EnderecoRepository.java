package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    public  PanacheQuery<Endereco> findByCep(String cep) {
        return find("cep", cep);
    }

        public PanacheQuery<Endereco> findAllInOrder(){
        return find("SELeCT e FROM Endereco e ORDER BY e.cep");
    }

}