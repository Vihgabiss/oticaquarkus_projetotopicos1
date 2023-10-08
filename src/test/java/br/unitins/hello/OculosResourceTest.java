package br.unitins.hello;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.service.OculosService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class OculosResourceTest {

    @Inject
    OculosService oculosService;

    @Test
    public void testFindAllOculos() {
        given()
                .when().get("/oculos")
                .then()
                .statusCode(200);
    }

}
