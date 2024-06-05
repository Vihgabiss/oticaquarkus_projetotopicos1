package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Venda;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

    public Pagamento findById(Long id) {
        return find("id", id).firstResult();
    }

    public List<Pagamento> findByValor(Double valor) {
        return list("valor", valor);
    }

    public List<Pagamento> findByVenda(Venda venda) {
        return list("venda", venda);
    }

}
