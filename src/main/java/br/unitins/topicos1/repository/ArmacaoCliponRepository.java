package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.ArmacaoClipon;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoCliponRepository implements PanacheRepository<ArmacaoClipon> {
    public List<ArmacaoClipon> findByReferencia(String referencia) {
        return find("UPPER(referencia) LIKE UPPER(?1) ", "%" + referencia + "%").list();
    }

    public List<ArmacaoClipon> findByFabricante(String fabricante) {
        return find("UPPER(fabricante.nome)  LIKE UPPER(?1)", "%" + fabricante + "%").list();
    }

}