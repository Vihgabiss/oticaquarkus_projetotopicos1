package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.EstiloOculos;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstiloOculosRepository implements PanacheRepository<EstiloOculos> {

    public PanacheQuery<EstiloOculos> findAllInOrder() {
        return find("SELeCT e FROM EstiloOculos e ORDER BY e.nome");
    }


    public PanacheQuery<EstiloOculos> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

    public PanacheQuery<EstiloOculos> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1)", "%" + descricao + "%");
    }

    public PanacheQuery<EstiloOculos> findByIdList(Long id){
        return find("CAST(id AS string) like ?1", "%"+id+"%");
    }

    public PanacheQuery<EstiloOculos> findInAllFields(String searchTerm){
        try {
            Long searchLong = Long.parseLong(searchTerm);
            return find("CAST(id AS string) like ?1 or lower(nome) like ?2 or lower(descricao) like ?2", "%"+searchLong+"%", "%"+searchTerm.toLowerCase()+"%");
        } catch (NumberFormatException e) {
            return find("lower(nome) like ?1 or lower(descricao) like ?1", "%"+searchTerm.toLowerCase()+"%");
        }
    }
    
}
