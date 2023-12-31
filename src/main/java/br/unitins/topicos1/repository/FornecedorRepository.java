package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {
    public List<Fornecedor> findByNome(String nome) {
        return list("UPPER(nome) LIKE UPPER(?1) ", "%" + nome.toUpperCase() + "%");
    }

    public List<Fornecedor> findByCnpj(String cnpj) {
        return find("UPPER(cnpj) LIKE UPPER(?1)", "%" + cnpj + "%").list();
    }

}