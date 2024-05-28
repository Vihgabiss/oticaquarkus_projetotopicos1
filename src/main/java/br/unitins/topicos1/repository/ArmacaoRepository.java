package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Armacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoRepository implements PanacheRepository<Armacao> {

    public Armacao findById(Long id) {
        return find("id", id).firstResult();
    }

    public List<Armacao> findByReferencia(String referencia) {
        return find("UPPER(referencia) LIKE UPPER(?1) ", "%" + referencia + "%").list();
    }

    public List<Armacao> findByFabricante(String fabricante) {
        return find("UPPER(fabricante.nome)  LIKE UPPER(?1)", "%" + fabricante + "%").list();
    }

}
