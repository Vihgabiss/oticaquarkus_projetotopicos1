package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.CartaoCredito;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartaoCreditoRepository implements PanacheRepository<CartaoCredito> {

    public CartaoCredito findById(Long id) {
        return find("id", id).firstResult();
    }

    public CartaoCredito findByNumeroCartao(String numeroCartao) {
        return find("numeroCartao", numeroCartao).firstResult();
    }
}
