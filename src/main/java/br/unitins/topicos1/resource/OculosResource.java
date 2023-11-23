// package br.unitins.topicos1.resource;

// import java.util.List;

// import br.unitins.topicos1.dto.OculosDTO;
// import br.unitins.topicos1.dto.OculosResponseDTO;
// import br.unitins.topicos1.service.OculosService;
// import jakarta.inject.Inject;
// import jakarta.transaction.Transactional;
// import jakarta.validation.Valid;
// import jakarta.ws.rs.*;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;
// import jakarta.ws.rs.core.Response.Status;

// @Path("/oculos")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// public class OculosResource {

//     @Inject
//     OculosService service;

//     @POST
//     @Transactional
//     public Response insert(@Valid OculosDTO dto) {
//         OculosResponseDTO retorno = service.insert(dto);
//         return Response.status(Status.CREATED).entity(retorno).build();
//     }

//     @PUT
//     @Transactional
//     @Path("/{id}")
//     public Response update(@Valid OculosDTO dto, @PathParam("id") Long id) {
//         OculosResponseDTO retorno = service.update(dto, id);
//         return Response.status(Status.OK).entity(retorno).build();
//     }

//     @DELETE
//     @Transactional
//     @Path("/{id}")
//     public Response delete(@PathParam("id") Long id) {
//         service.delete(id);
//         return Response.status(Status.NO_CONTENT).build();
//     }

//     @GET
//     @Path("/{id}")
//     public Response findById(@PathParam("id") Long id) {
//         OculosResponseDTO retorno = service.findById(id);
//         return Response.ok(retorno).build();
//     }

//     @GET
//     @Path("/referencia/{referencia}")
//     public Response findByReferencia(@PathParam("referencia") String referencia) {
//         List<OculosResponseDTO> retorno = service.findByReferencia(referencia);
//         return Response.ok(retorno).build();
//     }

//     @GET
//     public Response findByAll() {
//         List<OculosResponseDTO> retorno = service.findByAll();
//         return Response.ok(retorno).build();
//     }
// }
