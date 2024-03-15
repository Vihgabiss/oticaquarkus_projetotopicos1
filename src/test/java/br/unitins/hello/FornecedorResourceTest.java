// package br.unitins.hello;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.CoreMatchers.notNullValue;

// import org.junit.jupiter.api.Test;

// import br.unitins.topicos1.dto.FornecedorDTO;
// import br.unitins.topicos1.dto.FornecedorResponseDTO;
// import br.unitins.topicos1.dto.UsuarioDTO;
// import br.unitins.topicos1.dto.UsuarioResponseDTO;
// import br.unitins.topicos1.service.FornecedorService;
// import br.unitins.topicos1.service.JwtService;
// import br.unitins.topicos1.service.UsuarioService;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;

// @QuarkusTest
// public class FornecedorResourceTest {

//         @Inject
//         FornecedorService fornecedorService;

//         @Inject
//         JwtService jwtService;

//         @Inject
//         UsuarioService usuarioService;

//         @Test
//         public void testInsert() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Joao", "122.122.122-11",
//                                 "joao@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);

//                 FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                                 "Anitta Glasses",
//                                 "(63) 98000-0000",
//                                 "Rua X Bairro Y",
//                                 "anittag@ag.com",
//                                 "12.757.753/2352-68");

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .contentType("application/json")
//                                 .body(fornecedorDTO)
//                                 .when()
//                                 .post("/fornecedor")
//                                 .then()
//                                 .statusCode(201)
//                                 .body("id", notNullValue(),
//                                                 "nome", is("Anitta Glasses"),
//                                                 "cnpj", is("12.757.753/2352-68"));
//         }

//         @Test
//         public void testUpdate() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Joao", "122.122.122-22",
//                                 "joao1@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);

//                 FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                                 "Anitta",
//                                 "(63) 98000-0000",
//                                 "Rua X Bairro Y",
//                                 "anittag@ag.com",
//                                 "12.757.753/2352-68");

//                 FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);
//                 Long idFornecedor = fornecedorResponseDTO.id();

//                 FornecedorDTO fornecedorUpdate = new FornecedorDTO(
//                                 "Anitta",
//                                 "(63) 98000-0000",
//                                 "Rua X Bairro Y",
//                                 "anittag@ag.com",
//                                 "12.757.753/2352-68");

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .contentType("application/json")
//                                 .body(fornecedorUpdate)
//                                 .when()
//                                 .put("/fornecedor/" + idFornecedor)
//                                 .then()
//                                 .statusCode(204);

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .when()
//                                 .get("/fornecedor/" + idFornecedor)
//                                 .then()
//                                 .statusCode(200)
//                                 .body("nome", is("Anitta"), "cnpj", is("12.757.753/2352-68"));
//         }

//         @Test
//         public void testDelete() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Joao", "122.122.122-33",
//                                 "joao2@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);

//                 FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                                 "Anitta",
//                                 "(63) 98000-0000",
//                                 "Rua X Bairro Y",
//                                 "anittag@ag.com",
//                                 "12.757.753/2352-68");

//                 FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);
//                 Long idFornecedor = fornecedorResponseDTO.id();

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .when()
//                                 .delete("/fornecedor/" + idFornecedor)
//                                 .then()
//                                 .statusCode(204);

//         }

//         @Test
//         public void testFindById() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Joao", "122.122.122-44",
//                                 "joao3@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);

//                 FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                                 "Anitta",
//                                 "(63) 98000-0000",
//                                 "Rua X Bairro Y",
//                                 "anittag@ag.com",
//                                 "12.757.753/2352-68");

//                 FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);
//                 Long idFornecedor = fornecedorResponseDTO.id();

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .when()
//                                 .get("/fornecedor/" + idFornecedor)
//                                 .then()
//                                 .statusCode(200)
//                                 .body("nome", is("Anitta"), "cnpj", is("12.757.753/2352-68"));
//         }

//         @Test
//         public void testFindByNome() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Joao", "122.122.122-55",
//                                 "joao5@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);

//                 FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                                 "Anitta",
//                                 "(63) 98000-0000",
//                                 "Rua X Bairro Y",
//                                 "anittag@ag.com",
//                                 "12.757.753/2352-68");

//                 fornecedorService.insert(fornecedorDTO);
//                 String nome = "Anitta";

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .when()
//                                 .get("/fornecedor/search/nome/{nome}", nome)
//                                 .then()
//                                 .statusCode(200)
//                                 .body("nome[0]", is("Anitta"));
//         }

//         @Test
//         public void testFindByAll() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Joao", "122.122.122-61",
//                                 "joao7@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);
//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .when()
//                                 .get("/fornecedor")
//                                 .then()
//                                 .statusCode(200);
//         }

//         @Test
//         public void testFindByCNPJ() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Joao", "122.122.122-66",
//                                 "joao6@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);

//                 FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                                 "Anitta",
//                                 "(63) 98000-0000",
//                                 "Rua X Bairro Y",
//                                 "anittag@ag.com",
//                                 "12.757.753/2352-68");

//                 fornecedorService.insert(fornecedorDTO);
//                 String cnpj = "12.757.753/2352-68";

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .when()
//                                 .get("/fornecedor/cnpj/{cnpj}", cnpj)
//                                 .then()
//                                 .statusCode(200)
//                                 .body("cnpj[0]", is(cnpj));
//         }

// }
