package br.unitins.hello;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService usuarioService;

    @Test
    public void testFindByAll() {
        given()
                .when().get("/usuarios")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindById() throws Exception {
        UsuarioDTO user = new UsuarioDTO(
                "Joy Gates", "122.122.122.12",
                "gatesjoy@gmail.com", "202015",
                null);

        Long id = usuarioService.insert(user).id();

        given()
                .contentType(ContentType.JSON)
                .when().get("/usuarios/" + id)
                .then()
                .statusCode(200);

        UsuarioResponseDTO usu = usuarioService.findById(id);
        assertThat(usu.nome(), is("Joy Gates"));
        assertThat(usu.cpf(), is("122.122.122.12"));
        assertThat(usu.email(), is("gatesjoy@gmail.com"));

    }

    @Test
    public void testFindByNome() throws Exception {
        UsuarioDTO user = new UsuarioDTO(
                "Joy Gates Segundo", "133.133.122.12",
                "gatesjoy2@gmail.com", "212015",
                null);

        String nome = usuarioService.insert(user).nome();

        given()
                .contentType(ContentType.JSON)
                .when().get("/usuarios/search/nome/" + nome)
                .then()
                .statusCode(200);

        List<UsuarioResponseDTO> usu = usuarioService.findByNome(nome);
        assertThat(usu.get(0).nome(), is("Joy Gates Segundo"));
        assertThat(usu.get(0).cpf(), is("133.133.122.12"));
        assertThat(usu.get(0).email(), is("gatesjoy2@gmail.com"));
    }

    @Test
    public void testInsert() {
        UsuarioDTO user = new UsuarioDTO(
                "Bill Gates", "222.222.222-22",
                "gatess@gmail.com", "222",
                null);

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/usuarios")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Bill Gates"),
                        "cpf", is("222.222.222-22"),
                        "email", is("gatess@gmail.com"));
    }

    @Test
    public void testUpdate() throws Exception {
        UsuarioDTO user = new UsuarioDTO(
                "Bill Gatos", "333.222.222-22",
                "billgatos@gmail.com", "12345",
                null);

        UsuarioResponseDTO usuarioTest = usuarioService.insert(user);
        Long id = usuarioTest.id();

        UsuarioDTO userUpdate = new UsuarioDTO(
                "Bily Gatos", "333.222.222-22",
                "billgatos@gmail.com", "6655",
                null);

        given()
                .contentType(ContentType.JSON)
                .body(userUpdate)
                .when().put("/usuarios/" + id)
                .then()
                .statusCode(204);

        UsuarioResponseDTO usu = usuarioService.findById(id);
        assertThat(usu.nome(), is("Bily Gatos"));
    }

    @Test
    public void testDelete() throws Exception {
        UsuarioDTO user = new UsuarioDTO(
                "Bily Joel", "333.222.444-55",
                "bilyjoel@gmail.com", "12345",
                null);

        Long id = usuarioService.insert(user).id();

        given()
                .when().delete("/usuarios/" + id)
                .then()
                .statusCode(204);

        UsuarioResponseDTO userResponse = null;
        try {
            usuarioService.findById(id);
        } catch (NullPointerException e) {

        } finally {
            assertNull(userResponse);
        }
    }

    @Test
    public void testIsertTelefone() throws Exception {
        TelefoneDTO dto = new TelefoneDTO("68", "8845-6321");

        UsuarioDTO user = new UsuarioDTO(
                "Bily Joel Segundo", "333.444.222-56",
                "bilyjoel2@gmail.com", "12345",
                null);

        Long id = usuarioService.insert(user).id();

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().patch("/usuarios/insere-telefone/" + id)
                .then()
                .statusCode(204);

        UsuarioResponseDTO usu = usuarioService.findById(id);
        assertThat(usu.listaTelefone().size(), is(1));
        TelefoneResponseDTO tel = usu.listaTelefone().get(0);
        assertThat(tel.codigoArea(), is("68"));
        assertThat(tel.numero(), is("8845-6321"));
    }

    @Test
    public void testUpdateTelefone() throws Exception {
        UsuarioDTO user = new UsuarioDTO(
                "Bily Joel Terceiro", "444.444.222-56",
                "bilyjoel3@gmail.com", "12345",
                null);

        UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
        Long id = userResponseDTO.id();

        TelefoneDTO dto = new TelefoneDTO("55", "9999-9999");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().patch("/usuarios/insere-telefone/" + id)
                .then()
                .statusCode(204);

        TelefoneDTO updatedTel = new TelefoneDTO("44", "2345-6789");
        if (!userResponseDTO.listaTelefone().isEmpty()) {
            Long idTelefone = userResponseDTO.listaTelefone().get(0).id();

            given()
                    .contentType(ContentType.JSON)
                    .body(updatedTel)
                    .when().patch("/usuarios/atualiza-telefone/" + id + "/" + idTelefone)
                    .then()
                    .statusCode(204);

            UsuarioResponseDTO usu = usuarioService.findById(id);
            TelefoneResponseDTO tel = usu.listaTelefone().get(0);
            assertThat(tel.codigoArea(), is("44"));
            assertThat(tel.numero(), is("2345-6789"));
        }

    }

    @Test
    public void deleteTelefone() throws Exception {
        UsuarioDTO user = new UsuarioDTO(
                "Bily Joel Quarto", "898.555.222-56",
                "bilyjoel4@gmail.com", "12345",
                null);

        UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
        Long id = userResponseDTO.id();

        TelefoneDTO dto = new TelefoneDTO("55", "9999-9999");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().patch("/usuarios/insere-telefone/" + id)
                .then()
                .statusCode(204);

        if (!userResponseDTO.listaTelefone().isEmpty()) {
            Long idTelefone = userResponseDTO.listaTelefone().get(0).id();

            given()
                    .when().delete("/usuarios/deleta-telefone/" + id + "/" + idTelefone)
                    .then()
                    .statusCode(204);

            UsuarioResponseDTO usu = usuarioService.findById(id);
            assertThat(usu.listaTelefone().size(), is(0));
        }

    }

    @Test
    public void testFindTelByID() throws Exception {
        UsuarioDTO user = new UsuarioDTO(
                "Bily Joel Quinto", "898.566.222-56",
                "bilyjoel5@gmail.com", "12345",
                null);

        UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
        Long id = userResponseDTO.id();

        TelefoneDTO dto = new TelefoneDTO("55", "8686-5252");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().patch("/usuarios/insere-telefone/" + id)
                .then()
                .statusCode(204);

        if (!userResponseDTO.listaTelefone().isEmpty()) {
            Long idTelefone = userResponseDTO.listaTelefone().get(0).id();

            given()
                    .contentType(ContentType.JSON)
                    .when().get("/usuarios/telefone/" + idTelefone)
                    .then()
                    .statusCode(200);

        TelefoneResponseDTO tel = usuarioService.findTelById(idTelefone);
        assertThat(tel.codigoArea(), is("55"));
        assertThat(tel.numero(), is("8686-5252"));
        }


    }

    @Test
    public void testFindTelByCodigoArea() throws Exception{
                UsuarioDTO user = new UsuarioDTO(
                "Bily Joel Último", "100.010.010-00",
                "bilyjoelthelast@gmail.com", "12345",
                null);

        UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
        Long id = userResponseDTO.id();

        TelefoneDTO dto = new TelefoneDTO("54", "1010-1000");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().patch("/usuarios/insere-telefone/" + id)
                .then()
                .statusCode(204);

        if (!userResponseDTO.listaTelefone().isEmpty()) {
            String codigoArea = userResponseDTO.listaTelefone().get(0).codigoArea();

            given()
                    .contentType(ContentType.JSON)
                    .when().get("/usuarios/telefone/" + codigoArea)
                    .then()
                    .statusCode(200);

        List<TelefoneResponseDTO> tel = usuarioService.findTelByCodigoArea(codigoArea);
        assertThat(tel.get(0).codigoArea(), is("54"));
        assertThat(tel.get(0).numero(), is("1010-1000"));
        }

    }

}
