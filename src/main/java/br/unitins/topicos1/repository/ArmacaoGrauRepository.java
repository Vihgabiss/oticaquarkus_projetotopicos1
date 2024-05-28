package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.ArmacaoGrau;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoGrauRepository implements PanacheRepository<ArmacaoGrau> {
    public ArmacaoGrau findById(Long id) {
        return find("id", id).firstResult();
    }

    public List<ArmacaoGrau> findByReferencia(String referencia) {
        return find("UPPER(referencia) LIKE UPPER(?1) ", "%" + referencia + "%").list();
    }

    public List<ArmacaoGrau> findByFabricante(String fabricante) {
        return find("UPPER(fabricante.nome)  LIKE UPPER(?1)", "%" + fabricante + "%").list();
    }

}