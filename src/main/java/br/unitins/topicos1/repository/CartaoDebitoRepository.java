package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.CartaoDebito;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class CartaoDebitoRepository implements PanacheRepository<CartaoDebito> {

    public CartaoDebito findById(Long id) {
        return find("id", id).firstResult();
    }

    public CartaoDebito findByNumeroCartao(String numeroCartao) {
        return find("numeroCartao", numeroCartao).firstResult();
    }
}
