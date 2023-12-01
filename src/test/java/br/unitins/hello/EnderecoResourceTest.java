// package br.unitins.hello;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.MatcherAssert.assertThat;


// import java.util.List;

// import org.junit.jupiter.api.Test;

// import br.unitins.topicos1.dto.EnderecoDTO;
// import br.unitins.topicos1.dto.EnderecoResponseDTO;
// import br.unitins.topicos1.dto.UsuarioDTO;
// import br.unitins.topicos1.dto.UsuarioResponseDTO;
// import br.unitins.topicos1.service.EnderecoService;
// import br.unitins.topicos1.service.UsuarioService;
// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.http.ContentType;
// import jakarta.inject.Inject;

// @QuarkusTest
// public class EnderecoResourceTest {

//     @Inject
//     EnderecoService enderecoService;

//     @Inject
//     UsuarioService usuarioService;

//     @Test
//     public void testFindByAll() {
//         given()
//                 .when().get("/endereco")
//                 .then()
//                 .statusCode(200);
//     }

//     @Test
//     public void testInsert() throws Exception {
//         UsuarioDTO user = new UsuarioDTO(
//                 "Bily Gatos", "333.222.222-22",
//                 "billgatos@gmail.com", "6655",
//                 null);

//         UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
//         Long idUsuario = userResponseDTO.id();

//         EnderecoDTO endDTO = new EnderecoDTO(
//                 "77005-030", "110 sul",
//                 "alameda 10", 10, "casa amarela",
//                 "Palmas");

//         given()
//                 .contentType(ContentType.JSON)
//                 .body(endDTO)
//                 .when().post("/endereco/insere-endereco/" + idUsuario)
//                 .then()
//                 .statusCode(201)
//                 .body("cep", is("77005-030"),
//                         "bairro", is("110 sul"),
//                         "rua", is("alameda 10"),
//                         "numero", is(10),
//                         "complemento", is("casa amarela"),
//                         "cidade", is("Palmas"));

//     }

//     @Test
//     public void testUpdate() throws Exception {
//         UsuarioDTO user = new UsuarioDTO(
//                 "Bily Cães", "111.222.888-22",
//                 "billcaes@gmail.com", "6655",
//                 null);

//         UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
//         Long idUsuario = userResponseDTO.id();

//         EnderecoDTO endDTO = new EnderecoDTO(
//                 "77005-020", "110 norte",
//                 "alameda 10", 10, "casa azul",
//                 "Palmas");

//         given()
//                 .contentType(ContentType.JSON)
//                 .body(endDTO)
//                 .when().post("/endereco/insere-endereco/" + idUsuario)
//                 .then()
//                 .statusCode(201);

//         EnderecoDTO updatedEndDTO = new EnderecoDTO("77005-040", "210 sul",
//                 "alameda 15", 15, "casa portão marrom",
//                 "Palmas");

//         if (!userResponseDTO.listaEndereco().isEmpty()) {
//             Long idEndereco = userResponseDTO.listaEndereco().get(0).id();

//             given()
//                     .contentType(ContentType.JSON)
//                     .body(updatedEndDTO)
//                     .when().put("/endereco/atualiza-endereco/" + idUsuario + "/" + idEndereco)
//                     .then()
//                     .statusCode(204);

//             EnderecoResponseDTO endUpdated = enderecoService.findById(idEndereco);
//             assertThat(endUpdated.bairro(), is("210 sul"));
//             assertThat(endUpdated.cep(), is("77005-040"));
//             assertThat(endUpdated.rua(), is("alameda 15"));
//             assertThat(endUpdated.numero(), is(15));
//             assertThat(endUpdated.complemento(), is("casa portão marrom"));
//             assertThat(endUpdated.cidade(), is("Palmas"));
//         }

//     }

//     @Test
//     public void testDelete() throws Exception {
//         UsuarioDTO user = new UsuarioDTO(
//                 "Bily Birds", "111.222.363-22",
//                 "billybirds@gmail.com", "6655",
//                 null);

//         UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
//         Long idUsuario = userResponseDTO.id();

//         EnderecoDTO endDTO = new EnderecoDTO(
//                 "77005-022", "112 norte",
//                 "alameda 10", 10, "casa verde",
//                 "Palmas");

//         given()
//                 .contentType(ContentType.JSON)
//                 .body(endDTO)
//                 .when().post("/endereco/insere-endereco/" + idUsuario)
//                 .then()
//                 .statusCode(201);

//         if (!userResponseDTO.listaEndereco().isEmpty()) {
//             Long idEndereco = userResponseDTO.listaEndereco().get(0).id();

//             given()
//                     .when().delete("/endereco/deleta-endereco/" + idUsuario + "/" + idEndereco)
//                     .then()
//                     .statusCode(204);

//             UsuarioResponseDTO usu = usuarioService.findById(idUsuario);
//             assertThat(usu.listaEndereco().size(), is(0));

//         }

//     }

//         @Test
//         public void testFindById() throws Exception{
//          UsuarioDTO user = new UsuarioDTO(
//                 "Bily Horses", "111.363.363-22",
//                 "billyhorses@gmail.com", "6655",
//                 null);

//         UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
//         Long idUsuario = userResponseDTO.id();

//         EnderecoDTO endDTO = new EnderecoDTO(
//                 "77005-400", "210 sul",
//                 "alameda 10", 10, "casa branca",
//                 "Palmas");

//             Long idEndereco = enderecoService.insert(idUsuario, endDTO).id();

//             given()
//             .contentType(ContentType.JSON)
//             .when().get("/endereco/" + idEndereco)
//             .then()
//             .statusCode(200);

//             EnderecoResponseDTO endereco = enderecoService.findById(idEndereco);
//             assertThat(endereco.bairro(), is("210 sul"));
//             assertThat(endereco.cep(), is("77005-400"));
//             assertThat(endereco.rua(), is("alameda 10"));
//             assertThat(endereco.numero(), is(10));
//             assertThat(endereco.complemento(), is("casa branca"));
//             assertThat(endereco.cidade(), is("Palmas"));
//         }

//         @Test
//         public void testFindByCep() throws Exception{
//          UsuarioDTO user = new UsuarioDTO(
//                 "Bily Fish", "398.363.363-22",
//                 "billyfish@gmail.com", "6655",
//                 null);

//         UsuarioResponseDTO userResponseDTO = usuarioService.insert(user);
//         Long idUsuario = userResponseDTO.id();

//         EnderecoDTO endDTO = new EnderecoDTO(
//                 "77041-633", "604 Norte",
//                 "alameda 19", 50, "condominio portal do lago",
//                 "Salvador");

//             String cep = enderecoService.insert(idUsuario, endDTO).cep();

//             given()
//             .contentType(ContentType.JSON)
//             .when().get("/endereco/cep/" + cep)
//             .then()
//             .statusCode(200);

//             List<EnderecoResponseDTO> endereco = enderecoService.findByCep(cep);
//             assertThat(endereco.get(0).bairro(), is("604 Norte"));
//             assertThat(endereco.get(0).cep(), is("77041-633"));
//             assertThat(endereco.get(0).rua(), is("alameda 19"));
//             assertThat(endereco.get(0).numero(), is(50));
//             assertThat(endereco.get(0).complemento(), is("condominio portal do lago"));
//             assertThat(endereco.get(0).cidade(), is("Salvador"));
//         }
// }
