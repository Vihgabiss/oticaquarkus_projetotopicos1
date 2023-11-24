package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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

    @PUT
    @Path("/{id}")
    public Response update(EstadoDTO dto, @PathParam("id") Long id){
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.noContent().build();
    
    }

    @GET
    @Path("/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }

    @PATCH
    @Path("/insere-cidade/{idEstado}")
    public Response insertCidade(CidadeDTO dto, @PathParam("idEstado") Long idEstado){
        service.insertCidade(idEstado, dto);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/atualiza-cidade/{idEstado}/{idCidade}")
    public Response updateCidade(CidadeDTO dto, @PathParam("idEstado") Long idEstado, @PathParam("idCidade") Long idCidade){
        service.updateCidade(idEstado, idCidade, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deleta-cidade/{idEstado}/{idCidade}")
    public Response deleteCidade(@PathParam("idEstado") Long idEstado, @PathParam("idCidade") Long idCidade){
        service.deleteCidade(idEstado, idCidade);
        return Response.noContent().build();
    }

    @GET
    @Path("/allCities")
    public Response findAllCities(){
        return Response.ok(service.findAllCities()).build();
    }

}
