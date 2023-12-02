package br.unitins.hello;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.FornecedorService;
import br.unitins.topicos1.service.JwtService;
import br.unitins.topicos1.service.MarcaService;
import br.unitins.topicos1.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MarcaResourceTest {

    @Inject
    MarcaService marcaService;

    @Inject 
    FornecedorService fornecedorService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

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

        MarcaDTO dto = new MarcaDTO(
                "Nike",
                idFornecedor);

        given()
            .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/marca")
                .then().statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Nike"));
    }

//     @Test
//     public void testUpdate() {
//         // Create a new Fornecedor
//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta",
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
//                         "nome", is("Anitta"),
//                         "cnpj", is("12.757.753/2352-68"))
//                 .extract().as(FornecedorResponseDTO.class);

//         MarcaDTO dto = new MarcaDTO(
//                 "Nike",
//                 fornecedorResponseDTO);

//         MarcaResponseDTO marcaResponseDTO = given()
//                 .contentType(ContentType.JSON)
//                 .body(dto)
//                 .when().post("/marca")
//                 .then().statusCode(201)
//                 .body("id", notNullValue(),
//                         "nome", is("Nike"),
//                         "fornecedor.cnpj", is("12.757.753/2352-68"))
//                 .extract().as(MarcaResponseDTO.class);

//         dto = new MarcaDTO(
//                 "Nike Updated",
//                 fornecedorResponseDTO);

//         given()
//                 .contentType(ContentType.JSON)
//                 .body(dto)
//                 .when().put("/marca/{id}", marcaResponseDTO.id())
//                 .then()
//                 .statusCode(204);

//         given()
//                 .when().get("/marca/{id}", marcaResponseDTO.id())
//                 .then()
//                 .statusCode(200)
//                 .body("nome", is("Nike Updated"),
//                         "fornecedor.cnpj", is("12.757.753/2352-68"));
//     }

//     @Test
//     public void testDelete() {
//         FornecedorDTO fornecedorDTO = new FornecedorDTO(
//                 "Anitta",
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
//                         "nome", is("Anitta"),
//                         "cnpj", is("12.757.753/2352-68"))
//                 .extract().as(FornecedorResponseDTO.class);

//         MarcaDTO dto = new MarcaDTO(
//                 "Nike",
//                 fornecedorResponseDTO);

//         MarcaResponseDTO marcaResponseDTO = given()
//                 .contentType(ContentType.JSON)
//                 .body(dto)
//                 .when().post("/marca")
//                 .then().statusCode(201)
//                 .body("id", notNullValue(),
//                         "nome", is("Nike"),
//                         "fornecedor.cnpj", is("12.757.753/2352-68"))
//                 .extract().as(MarcaResponseDTO.class);

//         given()
//                 .when().delete("/marca/{id}", marcaResponseDTO.id())
//                 .then()
//                 .statusCode(204);

//         given()
//                 .when().get("/marca/" + marcaResponseDTO.id())
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

//         FornecedorResponseDTO fornecedorResponseDTO = given()
//                 .contentType(ContentType.JSON)
//                 .body(fornecedorDTO)
//                 .when().post("/fornecedor")
//                 .then().statusCode(201)
//                 .body("id", notNullValue(),
//                         "nome", is("Anitta"),
//                         "cnpj", is("12.757.753/2352-68"))
//                 .extract().as(FornecedorResponseDTO.class);

//         MarcaDTO dto = new MarcaDTO(
//                 "Nike",
//                 fornecedorResponseDTO);

//         MarcaResponseDTO marcaResponseDTO = given()
//                 .contentType(ContentType.JSON)
//                 .body(dto)
//                 .when().post("/marca")
//                 .then().statusCode(201)
//                 .body("id", notNullValue(),
//                         "nome", is("Nike"),
//                         "fornecedor.cnpj", is("12.757.753/2352-68"))
//                 .extract().as(MarcaResponseDTO.class);

//         given()
//                 .when().get("/marca/{id}", marcaResponseDTO.id())
//                 .then()
//                 .statusCode(200)
//                 .body("nome", is("Nike"),
//                         "fornecedor.cnpj", is("12.757.753/2352-68"));
//     }

//     // @Test
//     // public void testFindByNome() {
//     // FornecedorDTO fornecedorDTO = new FornecedorDTO(
//     // "Anitta",
//     // "(63) 98000-0000",
//     // "Rua X Bairro Y",
//     // "anittag@ag.com",
//     // "12.757.753/2352-68");

//     // FornecedorResponseDTO fornecedorResponseDTO = given()
//     // .contentType(ContentType.JSON)
//     // .body(fornecedorDTO)
//     // .when().post("/fornecedor")
//     // .then().statusCode(201)
//     // .body("id", notNullValue(),
//     // "nome", is("Anitta"),
//     // "cnpj", is("12.757.753/2352-68"))
//     // .extract().as(FornecedorResponseDTO.class);

//     // MarcaDTO dto = new MarcaDTO(
//     // "Nike",
//     // fornecedorResponseDTO);

//     // given()
//     // .contentType(ContentType.JSON)
//     // .body(dto)
//     // .when().post("/marca")
//     // .then().statusCode(201)
//     // .body("id", notNullValue(),
//     // "nome", is("Nike"),
//     // "fornecedor.cnpj", is("12.757.753/2352-68"));

//     // String nome = "Nike";

//     // given()
//     // .when().get("/marca/search/nome/{nome}", nome)
//     // .then()
//     // .log().all()
//     // .statusCode(404)
//     // .body("size()", greaterThan(0))
//     // .body("[0].nome", is("Nike"));

//     // }

//     @Test
//     public void testFindByAll() {
//         given()
//                 .when().get("/marca")
//                 .then()
//                 .statusCode(200);
//     }
}
