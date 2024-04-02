package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.EstiloOculos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstiloOculosRepository implements PanacheRepository<EstiloOculos>{
    
        public List<EstiloOculos> findAllInOrder(){
        return find("SELeCT e FROM EstiloOculos e ORDER BY e.nome").list();
    }
}
