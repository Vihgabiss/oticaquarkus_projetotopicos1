package br.unitins.hello;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.service.FornecedorService;
import jakarta.inject.Inject;

public class FornecedorResourceTest {
    @Inject
    FornecedorService fornecedorService;

    @Test
    public void testFindByAll() {
        given()
                .when().get("/fornecedor")
                .then()
                .statusCode(200);
    }

}
