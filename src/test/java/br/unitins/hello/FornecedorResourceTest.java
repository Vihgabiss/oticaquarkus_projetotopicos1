// package br.unitins.hello;

// import static io.restassured.RestAssured.given;

// import org.junit.jupiter.api.Test;

// import br.unitins.topicos1.service.FornecedorService;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;

// @QuarkusTest
// public class FornecedorResourceTest {
//     @Inject
//     FornecedorService fornecedorService;

//     @Test
//     public void testFindByAll() {
//         given()
//                 .when().get("/fornecedor")
//                 .then()
//                 .statusCode(200);
//     }

// }
