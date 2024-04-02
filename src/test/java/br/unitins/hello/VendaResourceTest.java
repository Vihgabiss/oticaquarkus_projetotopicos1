// package br.unitins.hello;

// import static io.restassured.RestAssured.given;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.Test;

// import br.unitins.topicos1.dto.ItemVendaDTO;
// import br.unitins.topicos1.dto.UsuarioDTO;
// import br.unitins.topicos1.dto.UsuarioResponseDTO;
// import br.unitins.topicos1.dto.VendaDTO;
// import br.unitins.topicos1.service.JwtService;
// import br.unitins.topicos1.service.UsuarioService;
// import br.unitins.topicos1.service.VendaService;
// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.http.ContentType;
// import jakarta.inject.Inject;

// @QuarkusTest
// public class VendaResourceTest {

//     @Inject
//     UsuarioService usuarioService;

//     @Inject
//     JwtService jwtService;

//     @Inject
//     VendaService vendaService;

//     @Test
//     public void testfindAll(){
//                 UsuarioDTO adm = new UsuarioDTO(
//                 "Ana", "998.999.122-11",
//                 "ana@gmail.com", "20220",
//                 2, null);

//         UsuarioResponseDTO usuario = usuarioService.insert(adm);

//         String token = jwtService.generateJwt(usuario);

//         given()
//         .headers("Authorization", "Bearer " + token)
//         .when().get("/venda")
//         .then()
//         .statusCode(200);

//     }

//     @Test
//     public void testfindById(){
//                 UsuarioDTO adm = new UsuarioDTO(
//                 "Ana", "998.998.122-11",
//                 "ana1@gmail.com", "20220",
//                 2, null);

//         UsuarioResponseDTO usuario = usuarioService.insert(adm);

//         String token = jwtService.generateJwt(usuario);

//         ItemVendaDTO itemVenda = new ItemVendaDTO(2, 200.00, 1L);
//         List<ItemVendaDTO> itens = new ArrayList<>();
//         itens.add(itemVenda);
        

//         VendaDTO venda = new VendaDTO(1, itens, 2);
//         Long idVenda = vendaService.insert(venda, "ana1@gmail.com").id();

//         given()
//         .headers("Authorization", "Bearer " + token)
//         .contentType(ContentType.JSON)
//         .when().get("/venda/"+idVenda)
//         .then()
//         .statusCode(200);

//     }

//     @Test
//     public void testInsert(){
//                 UsuarioDTO adm = new UsuarioDTO(
//                 "Ana", "998.997.122-11",
//                 "ana2@gmail.com", "20220",
//                 2, null);

//         UsuarioResponseDTO usuario = usuarioService.insert(adm);

//         String token = jwtService.generateJwt(usuario);

//         ItemVendaDTO itemVenda = new ItemVendaDTO(2, 200.00, 1L);
//         List<ItemVendaDTO> itens = new ArrayList<>();
//         itens.add(itemVenda);
        

//         VendaDTO venda = new VendaDTO(1, itens, 2);

//         vendaService.insert(venda, "ana2@gmail.com");
        

//         given()
//         .headers("Authorization", "Bearer " + token)
//         .contentType(ContentType.JSON)
//         .body(venda)
//         .when().post("/venda")
//         .then()
//         .statusCode(201);
//     }

//        @Test
//     public void testfindAllByUserEmail(){
//                 UsuarioDTO adm = new UsuarioDTO(
//                 "Ana", "998.977.122-11",
//                 "ana3@gmail.com", "20220",
//                 2, null);

//         UsuarioResponseDTO usuario = usuarioService.insert(adm);
//         String email = usuario.email();

//         String token = jwtService.generateJwt(usuario);

//         given()
//         .headers("Authorization", "Bearer " + token)
//         .when().get("/venda/vendas/"+email)
//         .then()
//         .statusCode(200);

//     }
// }
