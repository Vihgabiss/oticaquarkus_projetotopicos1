package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado>{
        
    public List<Estado> findByNome(String nome){
            return find("UPPER(nome) LIKE UPPER(?1)", "%"+nome+"%").list();
    }

        public Estado findBySigla(String sigla){
            try{
                 return find("UPPER(sigla) LIKE UPPER(?1)", sigla).singleResult();
            }catch(NoResultException e){
                e.printStackTrace();
                return null;
            }
           
    }
}
