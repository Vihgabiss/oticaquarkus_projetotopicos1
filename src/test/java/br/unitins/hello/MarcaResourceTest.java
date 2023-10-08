package br.unitins.hello;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.service.MarcaService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class MarcaResourceTest {

    @Inject
    MarcaService marcaService;

    @Test
    public void testFindByAll() {
        given()
                .when().get("/marca")
                .then()
                .statusCode(200);
    }

    
}
