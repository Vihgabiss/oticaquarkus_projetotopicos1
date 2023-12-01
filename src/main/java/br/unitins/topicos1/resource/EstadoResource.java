package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.service.EstadoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
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
    @RolesAllowed({"Admin"})
    public Response insert(EstadoDTO dto){
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response findAll(){
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    @Path("/sigla/{sigla}")
    public Response findBySigla(@Valid @PathParam("sigla") String sigla){
        return Response.ok(service.findBySigla(sigla)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(EstadoDTO dto, @PathParam("id") Long id){
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.noContent().build();
    
    }

    @GET
    @Path("/nome/{nome}")
    @RolesAllowed({ "User", "Admin"})
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }

    @POST
    @Path("/insere-cidade/")
    @RolesAllowed({"Admin"})
    public Response insertCidade(CidadeDTO dto){
        service.insertCidade(dto);
        return Response.noContent().build();
    }

    @PUT
    @Path("/atualiza-cidade/{idEstado}/{idCidade}")
    @RolesAllowed({"Admin"})
    public Response updateCidade(CidadeDTO dto, @PathParam("idEstado") Long idEstado, @PathParam("idCidade") Long idCidade){
        service.updateCidade(idCidade, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deleta-cidade/{idEstado}/{idCidade}")
    @RolesAllowed({"Admin"})
    public Response deleteCidade(@PathParam("idEstado") Long idEstado, @PathParam("idCidade") Long idCidade){
        service.deleteCidade(idCidade);
        return Response.noContent().build();
    }

    @GET
    @Path("/allCities")
    @RolesAllowed({ "User", "Admin"})
    public Response findAllCities(){
        return Response.ok(service.findAllCities()).build();
    }

}
