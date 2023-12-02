package br.unitins.hello;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.CidadeResponseDTO;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.EnderecoService;
import br.unitins.topicos1.service.EstadoService;
import br.unitins.topicos1.service.JwtService;
import br.unitins.topicos1.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EnderecoResourceTest {

    @Inject
    EnderecoService enderecoService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    @Inject
    EstadoService estadoService;

    @Test
    public void testFindByAll() {
        UsuarioDTO user = new UsuarioDTO(
                "Maria", "999.122.122-11",
                "maria@gmail.com", "20220",
                2, null);

        UsuarioResponseDTO usuario = usuarioService.insert(user);

        String token = jwtService.generateJwt(usuario);

        given()
                .headers("Authorization", "Bearer " + token)
                .when().get("/endereco")
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

        EstadoDTO estado = new EstadoDTO("Acre", "AC");
        EstadoResponseDTO est = estadoService.insert(estado);
        Long idEstado = est.id();

        CidadeDTO cidade = new CidadeDTO("Rio Branco", idEstado);
        CidadeResponseDTO cid = estadoService.insertCidade(cidade);
        Long idCidade = cid.id();

        UsuarioDTO user = new UsuarioDTO(
                "Bily Gatos", "333.222.222-22",
                "billgatos@gmail.com", "6655", 1,
                null);

        UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
        Long idUsuario = userResponseDTO.id();

        EnderecoDTO endDTO = new EnderecoDTO(
                "77005-030", "110 sul",
                "alameda 10", 10, "casa amarela",
                idCidade);

        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(endDTO)
                .when().post("/endereco/insere-endereco/" + idUsuario)
                .then()
                .statusCode(201)
                .body("cep", is("77005-030"),
                        "bairro", is("110 sul"),
                        "rua", is("alameda 10"),
                        "numero", is(10),
                        "complemento", is("casa amarela"));

    }

    @Test
    public void testUpdate() {
        UsuarioDTO adm = new UsuarioDTO(
                "Maria", "997.122.122-11",
                "maria2@gmail.com", "20220",
                2, null);

        UsuarioResponseDTO usuario = usuarioService.insert(adm);

        String token = jwtService.generateJwt(usuario);

        UsuarioDTO user = new UsuarioDTO(
                "Bily Cães", "111.222.888-22",
                "billcaes@gmail.com", "6655", 1,
                null);

        UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
        Long idUsuario = userResponseDTO.id();

        EstadoDTO estado = new EstadoDTO("Amazonas", "AM");
        EstadoResponseDTO est = estadoService.insert(estado);
        Long idEstado = est.id();

        CidadeDTO cidade = new CidadeDTO("Manaus", idEstado);
        CidadeResponseDTO cid = estadoService.insertCidade(cidade);
        Long idCidade = cid.id();

        EnderecoDTO endDTO = new EnderecoDTO(
                "77005-020", "110 norte",
                "alameda 10", 10, "casa azul",
                idCidade);

        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(endDTO)
                .when().post("/endereco/insere-endereco/" + idUsuario)
                .then()
                .statusCode(201);

        EnderecoDTO updatedEndDTO = new EnderecoDTO("77005-040", "210 sul",
                "alameda 15", 15, "casa portão marrom",
                idCidade);

        if (!userResponseDTO.listaEndereco().isEmpty()) {
            Long idEndereco = userResponseDTO.listaEndereco().get(0).id();

            given()
                    .headers("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(updatedEndDTO)
                    .when().put("/endereco/atualiza-endereco/" + idUsuario + "/" + idEndereco)
                    .then()
                    .statusCode(204);

            EnderecoResponseDTO endUpdated = enderecoService.findById(idEndereco);
            assertThat(endUpdated.bairro(), is("210 sul"));
            assertThat(endUpdated.cep(), is("77005-040"));
            assertThat(endUpdated.rua(), is("alameda 15"));
            assertThat(endUpdated.numero(), is(15));
            assertThat(endUpdated.complemento(), is("casa portão marrom"));
        }

    }

    @Test
    public void testDelete() {
        UsuarioDTO adm = new UsuarioDTO(
                "Maria", "996.122.122-11",
                "maria3@gmail.com", "20220",
                2, null);

        UsuarioResponseDTO usuario = usuarioService.insert(adm);

        String token = jwtService.generateJwt(usuario);

        UsuarioDTO user = new UsuarioDTO(
                "Bily Birds", "111.222.363-22",
                "billybirds@gmail.com", "6655", 1,
                null);

        UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
        Long idUsuario = userResponseDTO.id();

        EstadoDTO estado = new EstadoDTO("Piauí", "PI");
        EstadoResponseDTO est = estadoService.insert(estado);
        Long idEstado = est.id();

        CidadeDTO cidade = new CidadeDTO("Teresina", idEstado);
        CidadeResponseDTO cid = estadoService.insertCidade(cidade);
        Long idCidade = cid.id();

        EnderecoDTO endDTO = new EnderecoDTO(
                "77005-022", "112 norte",
                "alameda 10", 10, "casa verde",
                idCidade);

        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(endDTO)
                .when().post("/endereco/insere-endereco/" + idUsuario)
                .then()
                .statusCode(201);

        if (!userResponseDTO.listaEndereco().isEmpty()) {
            Long idEndereco = userResponseDTO.listaEndereco().get(0).id();

            given()
                    .headers("Authorization", "Bearer " + token)
                    .when().delete("/endereco/deleta-endereco/" + idUsuario + "/" + idEndereco)
                    .then()
                    .statusCode(204);

            UsuarioResponseDTO usu = usuarioService.findById(idUsuario);
            assertThat(usu.listaEndereco().size(), is(0));

        }

    }

    @Test
    public void testFindById() {
        UsuarioDTO adm = new UsuarioDTO(
                "Maria", "995.122.122-11",
                "maria4@gmail.com", "20220",
                2, null);

        UsuarioResponseDTO usuario = usuarioService.insert(adm);

        String token = jwtService.generateJwt(usuario);


    UsuarioDTO user = new UsuarioDTO(
    "Bily Horses", "111.363.363-22",
    "billyhorses@gmail.com", "6655",1,
    null);

    UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
    Long idUsuario = userResponseDTO.id();

        EstadoDTO estado = new EstadoDTO("Maranhão", "MA");
        EstadoResponseDTO est = estadoService.insert(estado);
        Long idEstado = est.id();

        CidadeDTO cidade = new CidadeDTO("São Luís", idEstado);
        CidadeResponseDTO cid = estadoService.insertCidade(cidade);
        Long idCidade = cid.id();

    EnderecoDTO endDTO = new EnderecoDTO(
    "77005-400", "210 sul",
    "alameda 10", 10, "casa branca",
    idCidade);

    Long idEndereco = enderecoService.insert(idUsuario, endDTO).id();

    given()
    .headers("Authorization", "Bearer " + token)
    .contentType(ContentType.JSON)
    .when().get("/endereco/" + idEndereco)
    .then()
    .statusCode(200);

    EnderecoResponseDTO endereco = enderecoService.findById(idEndereco);
    assertThat(endereco.bairro(), is("210 sul"));
    assertThat(endereco.cep(), is("77005-400"));
    assertThat(endereco.rua(), is("alameda 10"));
    assertThat(endereco.numero(), is(10));
    assertThat(endereco.complemento(), is("casa branca"));
    }

    @Test
    public void testFindByCep() {
        UsuarioDTO adm = new UsuarioDTO(
                "Maria", "994.122.122-11",
                "maria5@gmail.com", "20220",
                2, null);

        UsuarioResponseDTO usuario = usuarioService.insert(adm);

        String token = jwtService.generateJwt(usuario);


    UsuarioDTO user = new UsuarioDTO(
    "Bily Fish", "398.363.363-22",
    "billyfish@gmail.com", "6655",1, 
    null);

    UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
    Long idUsuario = userResponseDTO.id();

        EstadoDTO estado = new EstadoDTO(" Minas Gerais", "MG");
        EstadoResponseDTO est = estadoService.insert(estado);
        Long idEstado = est.id();

        CidadeDTO cidade = new CidadeDTO("Patos de Minas", idEstado);
        CidadeResponseDTO cid = estadoService.insertCidade(cidade);
        Long idCidade = cid.id();

    EnderecoDTO endDTO = new EnderecoDTO(
    "77041-633", "604 Norte",
    "alameda 19", 50, "condominio portal do lago",
    idCidade);

    String cep = enderecoService.insert(idUsuario, endDTO).cep();

    given()
     .headers("Authorization", "Bearer " + token)
    .contentType(ContentType.JSON)
    .when().get("/endereco/cep/" + cep)
    .then()
    .statusCode(200);

    List<EnderecoResponseDTO> endereco = enderecoService.findByCep(cep);
    assertThat(endereco.get(0).bairro(), is("604 Norte"));
    assertThat(endereco.get(0).cep(), is("77041-633"));
    assertThat(endereco.get(0).rua(), is("alameda 19"));
    assertThat(endereco.get(0).numero(), is(50));
    assertThat(endereco.get(0).complemento(), is("condominio portal do lago"));
    }
}
