package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.OculosDTO;
import br.unitins.topicos1.dto.OculosResponseDTO;
import br.unitins.topicos1.service.OculosService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/oculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OculosResource {

    @Inject
    OculosService service;

    @POST
    @Transactional
    public Response insert(@Valid OculosDTO dto) {
        try {
            OculosResponseDTO retorno = service.insert(dto);
            return Response.status(201).entity(retorno).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(OculosDTO dto, @PathParam("id") Long id) {
        try {
            service.update(dto, id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        try {
            return Response.ok(service.findById(id)).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search/referencia/{referencia}")
    public Response findByReferencia(@PathParam("referencia") String referencia) {
        try {
            return Response.ok(service.findByReferencia(referencia)).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}