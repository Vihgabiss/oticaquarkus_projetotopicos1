package br.unitins.hello;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.FornecedorDTO;
import br.unitins.topicos1.dto.FornecedorResponseDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
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

        @Test
        public void testUpdate() {
                UsuarioDTO adm = new UsuarioDTO(
                                "Maria", "998.222.122-11",
                                "maria@gmail.com", "20220",
                                2, null);

                UsuarioResponseDTO usuario = usuarioService.insert(adm);

                String token = jwtService.generateJwt(usuario);

                FornecedorDTO fornecedor = new FornecedorDTO(
                                "Anitta",
                                "(63) 98000-0000",
                                "Rua X Bairro Y",
                                "anittag@ag.com",
                                "12.757.753/2352-68");
                FornecedorResponseDTO forn = fornecedorService.insert(fornecedor);
                Long idFornecedor = forn.id();

                MarcaDTO dto = new MarcaDTO(
                                "Nike",
                                idFornecedor);

                MarcaResponseDTO marcaTest = marcaService.insert(dto);
                Long idMarca = marcaTest.id();

                MarcaDTO upMarcaDTO = new MarcaDTO("Nikel", idFornecedor);
                given()
                                .headers("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .body(upMarcaDTO)
                                .when().put("/marca/" + idMarca)
                                .then().statusCode(204);

        }

        @Test
        public void testDelete() {

                UsuarioDTO user = new UsuarioDTO(
                                "Joao", "122.122.122-33",
                                "joao2@gmail.com", "20220",
                                2, null);

                UsuarioResponseDTO usuario = usuarioService.insert(user);

                String token = jwtService.generateJwt(usuario);

                FornecedorDTO fornecedorDTO = new FornecedorDTO(
                                "Anitta",
                                "(63) 98000-0000",
                                "Rua X Bairro Y",
                                "anittag@ag.com",
                                "12.757.753/2352-68");

                FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);
                Long idFornecedor = fornecedorResponseDTO.id();

                MarcaDTO dto = new MarcaDTO(
                                "Nike",
                                idFornecedor);

                MarcaResponseDTO marcaTest = marcaService.insert(dto);
                Long idMarca = marcaTest.id();

                given()
                                .header("Authorization", "Bearer " + token)
                                .when()
                                .delete("/marca/" + idMarca)
                                .then()
                                .statusCode(204);

                given()
                                .header("Authorization", "Bearer " + token)
                                .when()
                                .get("/marca/" + idMarca)
                                .then()
                                .statusCode(404);
        }

        @Test
        public void testFindById() {
                UsuarioDTO user = new UsuarioDTO(
                                "Joao", "122.122.122-44",
                                "joao10@gmail.com", "20220",
                                2, null);

                UsuarioResponseDTO usuario = usuarioService.insert(user);

                String token = jwtService.generateJwt(usuario);

                FornecedorDTO fornecedorDTO = new FornecedorDTO(
                                "Anitta",
                                "(63) 98000-0000",
                                "Rua X Bairro Y",
                                "anittag@ag.com",
                                "12.757.753/2352-68");

                FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);
                Long idFornecedor = fornecedorResponseDTO.id();

                MarcaDTO dto = new MarcaDTO(
                                "Nike",
                                idFornecedor);

                MarcaResponseDTO marcaTest = marcaService.insert(dto);
                Long idMarca = marcaTest.id();

                given()
                                .header("Authorization", "Bearer " + token)
                                .when()
                                .get("/marca/" + idMarca)
                                .then()
                                .statusCode(200)
                                .body("nome", is("Nike"));

                MarcaResponseDTO marca = marcaService.findById(idMarca);
                assertThat(marca.nome(), is("Nike"));

        }

        @Test
        public void testFindByNome() {
                UsuarioDTO user = new UsuarioDTO(
                                "Joao", "132.182.122-44",
                                "joao3@gmail.com", "20220",
                                2, null);

                UsuarioResponseDTO usuario = usuarioService.insert(user);

                String token = jwtService.generateJwt(usuario);

                FornecedorDTO fornecedorDTO = new FornecedorDTO(
                                "Anitta",
                                "(63) 98000-0000",
                                "Rua X Bairro Y",
                                "anittag@ag.com",
                                "12.757.753/2352-68");

                FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.insert(fornecedorDTO);
                Long idFornecedor = fornecedorResponseDTO.id();

                MarcaDTO dto = new MarcaDTO(
                                "Nike",
                                idFornecedor);

                MarcaResponseDTO marcaTest = marcaService.insert(dto);
                String marca = marcaTest.nome();

                given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/marca/search/nome/{nome}", marca)
                .then()
                .statusCode(200)
                .body("nome[0]", is("Nike"));

        }

        @Test
        public void testFindByAll() {
                UsuarioDTO user = new UsuarioDTO(
                                "Joao", "122.134.122-61",
                                "joao7@gmail.com", "20220",
                                2, null);

                UsuarioResponseDTO usuario = usuarioService.insert(user);

                String token = jwtService.generateJwt(usuario);
                given()
                                .header("Authorization", "Bearer " + token)
                                .when()
                                .get("/marca")
                                .then()
                                .statusCode(200);
        }
}
