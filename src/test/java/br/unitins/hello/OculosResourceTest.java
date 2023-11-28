// package br.unitins.hello;

// import static io.restassured.RestAssured.given;

// import org.junit.jupiter.api.Test;

// import br.unitins.topicos1.service.OculosService;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;

// @QuarkusTest
// public class OculosResourceTest {

//     @Inject
//     OculosService oculosService;

//     @Test
//     public void testFindAllOculos() {
//         given()
//                 .when().get("/oculos")
//                 .then()
//                 .statusCode(200);
//     }

//     @Inject
//     FornecedorService fornecedorService;

//     @Test
//     public void testInsertOculos() {

//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta Glasses",
//                 "(63) 98000-0000",
//                 "Rua X Bairro Y",
//                 "anittag@ag.com",
//                 "12.757.753/2352-68");

//         FornecedorResponseDTO fornecedorResponseDTO = given()
//                 .contentType(ContentType.JSON)
//                 .body(fornecedorDTO)
//                 .when().post("/fornecedor")
//                 .then().statusCode(201)
//                 .body("id", notNullValue(),
//                         "nome", is("Anitta Glasses"),
//                         "cnpj", is("12.757.753/2352-68"))
//                 .extract().as(FornecedorResponseDTO.class);

//         MarcaDTO marcaDTO = new MarcaDTO(
//                 "Nike",
//                 fornecedorResponseDTO);


                
//         MarcaResponseDTO marcaResponseDTO  = given()
//                 .contentType(ContentType.JSON)
//                 .body(marcaDTO)
//                 .when().post("/marca")
//                 .then().statusCode(201)
//                 .body("id", notNullValue(),
//                         "nome", is("Nike"),
//                         "fornecedor.cnpj", is("12.757.753/2352-68"));

//         OculosDTO oculosDTO = new OculosDTO(
//                 "SomeReference",
//                 "SomeColor",
//                 "SomeSize",
//                 100.0,
//                 150.0,
//                 10,
//                 marcaResponseDTO);

//         given()
//                 .contentType(ContentType.JSON)
//                 .body(oculosDTO)
//                 .when().post("/oculos")
//                 .then()
//                 .statusCode(201)
//                 .body("id", notNullValue())
//                 .body("referencia", is("SomeReference"))
//                 .body("cor", is("SomeColor"))
//                 .body("tamanho", is("SomeSize"))
//                 .body("precoCusto", is(100.0f))
//                 .body("precoVenda", is(150.0f))
//                 .body("quantidade", is(10))
//                 .body("marca", notNullValue());
//     }

//     @Test
//     public void testUpdateOculos() {
//         // Assuming there is an existing oculos with ID 1 in the system
//         OculosDTO updatedOculosDTO = new OculosDTO(
//                 "UpdatedReference",
//                 "UpdatedBrand",
//                 "UpdatedModel",
//                 150.0,
//                 "UpdatedDescription");

//         given()
//                 .contentType(ContentType.JSON)
//                 .body(updatedOculosDTO)
//                 .when().put("/oculos/1")
//                 .then()
//                 .statusCode(200)
//                 .body("id", notNullValue())
//                 .body("referencia", is("UpdatedReference"))
//                 .body("marca", is("UpdatedBrand"))
//                 .body("modelo", is("UpdatedModel"))
//                 .body("preco", is(150.0f))
//                 .body("descricao", is("UpdatedDescription"));
//     }

//     @Test
//     public void testDeleteOculos() {
//         given()
//                 .when().delete("/oculos/1")
//                 .then()
//                 .statusCode(204);
//     }

//     @Test
//     public void testFindByIdOculos() {
//         given()
//                 .when().get("/oculos/1")
//                 .then()
//                 .statusCode(200)
//                 .body("id", notNullValue())
//                 .body("referencia", notNullValue())
//                 .body("marca", notNullValue())
//                 .body("modelo", notNullValue())
//                 .body("preco", notNullValue())
//                 .body("descricao", notNullValue());
//     }

//     @Test
//     public void testFindByReferenciaOculos() {
//         given()
//                 .when().get("/oculos/referencia/SomeReference")
//                 .then()
//                 .statusCode(200)
//                 .body("size()", is(1))
//                 .body("[0].id", notNullValue())
//                 .body("[0].referencia", is("SomeReference"))
//                 .body("[0].marca", notNullValue())
//                 .body("[0].modelo", notNullValue())
//                 .body("[0].preco", notNullValue())
//                 .body("[0].descricao", notNullValue());
//     }

//     @Test
//     public void testFindByAllOculos() {
//         given()
//                 .when().get("/oculos")
//                 .then()
//                 .statusCode(200);
//     }
// }
