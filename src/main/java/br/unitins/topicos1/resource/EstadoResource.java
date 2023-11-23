package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/estado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {
    
    @Inject
    EstadoService service;

    @POST
    public Response insert(EstadoDTO dto){
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @GET
    public Response findAll(){
        return Response.ok(service.findByAll()).build();
    }

     @GET
    @Path("/sigla/{sigla}")
    public Response findBySigla(@Valid @PathParam("sigla") String sigla){
        return Response.ok(service.findBySigla(sigla)).build();
    }
}
