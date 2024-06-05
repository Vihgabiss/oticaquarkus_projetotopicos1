package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Cupom;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CupomRepository implements PanacheRepository<Cupom> {
    public PanacheQuery<Cupom> findAllInOrder() {
        return find("SELeCT c FROM Cupom c ORDER BY c.nome");
    }

    public Cupom findByNome(String nome) { // Correção para retornar apenas um cupom
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%").firstResult(); // Corrigido para 'nome'
    }

}
