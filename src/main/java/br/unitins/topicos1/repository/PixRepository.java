package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Pix;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PixRepository implements PanacheRepository<Pix> {
    public Pix findByChavePix(String chavePix) {
        return find("chavePix", chavePix).firstResult();
    }
}
