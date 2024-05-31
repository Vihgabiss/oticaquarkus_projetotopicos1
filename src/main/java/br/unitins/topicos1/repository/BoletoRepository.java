package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Boleto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoletoRepository implements PanacheRepository<Boleto> {

    public Boleto findById(Long id) {
        return find("id", id).firstResult();
    }

    public Boleto findByCodigoBarras(String codigoBarras) {
        return find("codigoBarras", codigoBarras).firstResult();
    }
}
