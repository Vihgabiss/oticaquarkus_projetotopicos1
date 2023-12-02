package br.unitins.hello;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.dto.OculosDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.FornecedorService;
import br.unitins.topicos1.service.JwtService;
import br.unitins.topicos1.service.MarcaService;
import br.unitins.topicos1.service.OculosService;
import br.unitins.topicos1.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class OculosResourceTest {

    @Inject
    OculosService oculosService;

    @Inject
    MarcaService marcaService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;
    @Inject
    FornecedorService fornecedorService;

    @Test
    public void testFindByAll() {
        UsuarioDTO user = new UsuarioDTO(
                "Chiquita", "111.222.333-44",
                "jchiquita@gmail.com", "20220",
                2, null);

        UsuarioResponseDTO usuario = usuarioService.insert(user);

        String token = jwtService.generateJwt(usuario);

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/oculos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        UsuarioDTO adm = new UsuarioDTO(
                "Maria", "998.122.122-11",
                "maria1@gmail.com", "20220",
                2, null);

        UsuarioResponseDTO usuario = usuarioService.insert(adm);

        String token = jwtService.generateJwt(usuario);

        FornecedorDTO fornecedorDTO = new FornecedorDTO(
                "Anitta Glasses",
                "(63) 98000-0000",
                "Rua X Bairro Y",
                "anittag@ag.com",
                "12.757.753/2352-68");

        FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);
        Long idFornecedor = fornecedorResponseDTO.id();

        MarcaDTO marcaDTO = new MarcaDTO(
                "Nike",
                idFornecedor);

        MarcaResponseDTO marcaResponseDTO = marcaService.insert(marcaDTO);
        Long idMarca = marcaResponseDTO.id();

        OculosDTO oculosDTO = new OculosDTO("ABC123",
                "#FF5733",
                "145",
                100.00,
                200.00,
                10,
                marcaResponseDTO,
                1,
                "oculos.jpeg");

        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(oculosDTO)
                .when().post("/oculos")
                .then()
                .statusCode(201)
                .body("referencia", is("ABC123"),
                        "cor", is("#FF5733"),
                        "tamanho", is("145"),
                        "precoCusto", is(100.00F),
                        "precoVenda", is(200.00F),
                        "quantidade", is(10));
    }

    @Test
    public void testDeleteOculos() {
        given()
                .when().delete("/oculos/1")
                .then()
                .statusCode(401);
    }

}