// package br.unitins.hello;

// import br.unitins.topicos1.dto.ArmacaoDTO;
// import br.unitins.topicos1.dto.MarcaDTO;
// import br.unitins.topicos1.dto.MarcaResponseDTO;
// import br.unitins.topicos1.dto.UsuarioDTO;
// import br.unitins.topicos1.dto.UsuarioResponseDTO;
// import br.unitins.topicos1.service.ArmacaoService;
// import br.unitins.topicos1.service.FornecedorService;
// import br.unitins.topicos1.service.JwtService;
// import br.unitins.topicos1.service.MarcaService;
// import br.unitins.topicos1.service.UsuarioService;
// import jakarta.inject.Inject;
// import jakarta.mail.internet.ContentType;

// @QuarkusTest
// public class ArmacaoResourceTest {

//         @Inject
//         ArmacaoService armacaoService;

//         @Inject
//         MarcaService marcaService;

//         @Inject
//         UsuarioService usuarioService;

//         @Inject
//         JwtService jwtService;
//         @Inject
//         FornecedorService fornecedorService;

//         @Test
//         public void testFindByAll() {
//                 UsuarioDTO user = new UsuarioDTO(
//                                 "Chiquita", "111.222.333-44",
//                                 "jchiquita@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(user);

//                 String token = jwtService.generateJwt(usuario);

//                 given()
//                                 .header("Authorization", "Bearer " + token)
//                                 .when()
//                                 .get("/armacao")
//                                 .then()
//                                 .statusCode(200);
//         }

//         @Test
//         public void testInsert() {
//                 UsuarioDTO adm = new UsuarioDTO(
//                                 "Maria", "998.122.122-11",
//                                 "maria1@gmail.com", "20220",
//                                 2, null);

//                 UsuarioResponseDTO usuario = usuarioService.insert(adm);

//                 String token = jwtService.generateJwt(usuario);
//                 MarcaDTO marcaDTO = new MarcaDTO(
//                                 "Nike");

//                 MarcaResponseDTO marcaResponseDTO = marcaService.insert(marcaDTO);

//                 ArmacaoDTO armacaoDTO = new ArmacaoDTO("ABC123",
//                                 "#FF5733",
//                                 "145",
//                                 100.00,
//                                 200.00,
//                                 10,
//                                 marcaResponseDTO,
//                                 1,
//                                 "armacao.jpeg");

//                 given()
//                                 .headers("Authorization", "Bearer " + token)
//                                 .contentType(ContentType.JSON)
//                                 .body(armacaoDTO)
//                                 .when().post("/armacao")
//                                 .then()
//                                 .statusCode(201)
//                                 .body("referencia", is("ABC123"),
//                                                 "cor", is("#FF5733"),
//                                                 "tamanho", is("145"),
//                                                 "precoCusto", is(100.00F),
//                                                 "precoVenda", is(200.00F),
//                                                 "quantidade", is(10));
//         }

//         @Test
//         public void testDeleteArmacao() {
//                 given()
//                                 .when().delete("/armacao/1")
//                                 .then()
//                                 .statusCode(401);
//         }

// }