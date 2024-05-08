// package br.unitins.topicos1.repository;

// import java.util.List;

// import br.unitins.topicos1.model.Venda;
// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import jakarta.enterprise.context.ApplicationScoped;

// @ApplicationScoped
// public class VendaRepository implements PanacheRepository<Venda> {
//     public List<Venda> findAll(String email) {
//         return find("usuario.email = ?1", email).list();
//     }

//     public List<Venda> findAll(Long idUsuario) {
//         return find("usuario.id = ?1", idUsuario).list();
//     }

// }
