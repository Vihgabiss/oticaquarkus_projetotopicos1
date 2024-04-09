package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado>{
        
    public PanacheQuery<Estado> findByNome(String nome){
            return find("UPPER(nome) LIKE UPPER(?1)", "%"+nome+"%");
    }

        public Estado findBySigla(String sigla){
            try{
                 return find("UPPER(sigla) LIKE UPPER(?1)", sigla).singleResult();
            }catch(NoResultException e){
                e.printStackTrace();
                return null;
            }
           
    }

        public PanacheQuery<Estado> findAllInOrder(){
        return find("SELECT c FROM Estado c ORDER BY c.nome");
    }
}
