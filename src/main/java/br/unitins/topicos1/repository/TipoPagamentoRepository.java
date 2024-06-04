package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.TipoPagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoPagamentoRepository implements PanacheRepository<TipoPagamento> {

    public TipoPagamento findById(Long id) {
        return find("id", id).firstResult();
    }

    public TipoPagamento findByNome(String nome) {
        return find("nome", nome).singleResult();
    }

}