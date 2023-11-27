// // package br.unitins.hello;

// // import static io.restassured.RestAssured.given;
// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.CoreMatchers.notNullValue;

// // import org.junit.jupiter.api.Test;

// // import br.unitins.topicos1.service.FornecedorService;
// // import io.quarkus.test.junit.QuarkusTest;
// // import jakarta.inject.Inject;

// // @QuarkusTest
// // public class FornecedorResourceTest {
// //     @Inject
// //     FornecedorService fornecedorService;

// //     @Test
// //     public void testFindByAll() {
// //         given()
// //                 .when().get("/fornecedor")
// //                 .then()
// //                 .statusCode(200);
// //     }

// // }
// import br.unitins.topicos1.dto.FornecedorDTO;
// import br.unitins.topicos1.dto.FornecedorResponseDTO;
// import br.unitins.topicos1.service.FornecedorService;
// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.http.ContentType;
// import jakarta.inject.Inject;

// @QuarkusTest
// public class FornecedorResourceTest {

//     @Inject
//     FornecedorService fornecedorService;

//     @Test
//     public void testInsert() {
//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta Glasses",
//                 "(63) 98000-0000",
//                 "Rua X Bairro Y",
//                 "anittag@ag.com",
//                 "12.757.753/2352-68");

//         fornecedorService.insert(fornecedorDTO);

//         given()
//                 .contentType(ContentType.JSON)
//                 .body(fornecedorDTO)
//                 .when().post("/fornecedor")
//                 .then().statusCode(201)
//                 .body("id", notNullValue(),
//                         "nome", is("Anitta Glasses"),
//                         "cnpj", is("12.757.753/2352-68"));

//     }

//     @Test
//     public void testUpdate() {
//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta",
//                 "(63) 98000-0000",
//                 "Rua X Bairro Y",
//                 "anittag@ag.com",
//                 "12.757.753/2352-68");

//         FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);

//         Long idFornecedor = fornecedorResponseDTO.id();

//         FornecedorDTO fornecedorUpdate = new FornecedorDTO(
//                 "Anitta",
//                 "(63) 98000-0000",
//                 "Rua X Bairro Y",
//                 "anittag@ag.com",

//                 "12.757.753/2352-68");

//         fornecedorService.update(fornecedorUpdate, idFornecedor);

//         given()
//                 .when().get("/fornecedor/" + idFornecedor)
//                 .then()
//                 .statusCode(200)
//                 .body("nome", is("Anitta"), "cnpj", is("12.757.753/2352-68"));
//     }

//     @Test
//     public void testDelete() {
//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta",
//                 "(63) 98000-0000",
//                 "Rua X Bairro Y",
//                 "anittag@ag.com",
//                 "12.757.753/2352-68");

//         FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);

//         Long idFornecedor = fornecedorResponseDTO.id();

//         fornecedorService.delete(idFornecedor);

//         given()
//                 .when().get("/fornecedor/" + idFornecedor)
//                 .then()
//                 .statusCode(404);
//     }

//     @Test
//     public void testFindById() {
//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta",
//                 "(63) 98000-0000",
//                 "Rua X Bairro Y",
//                 "anittag@ag.com",
//                 "12.757.753/2352-68");

//         FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);

//         Long idFornecedor = fornecedorResponseDTO.id();

//         given()
//                 .when().get("/fornecedor/" + idFornecedor)
//                 .then()
//                 .statusCode(200)
//                 .body("nome", is("Anitta"), "cnpj", is("12.757.753/2352-68"));
//     }

//     @Test
//     public void testFindByNome() {
//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta",
//                 "(63) 98000-0000",
//                 "Rua X Bairro Y",
//                 "anittag@ag.com",
//                 "12.757.753/2352-68");

//         FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);

//         String nome = "Anitta";

//         given()
//                 .when().get("/fornecedor/search/nome/{nome}", nome)
//                 .then()
//                 .statusCode(200)
//                 .body("nome[0]", is("Anitta"));
//     }

//     @Test
//     public void testFindByAll() {
//         given()
//                 .when().get("/fornecedor")
//                 .then()
//                 .statusCode(200);
//     }
// }
