package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    UsuarioService service;

    @POST
    public Response insert(UsuarioDTO dto) throws Exception{
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PATCH
    @Transactional
    @Path("/insere-telefone/{idUsuario}")
    public Response insertTelefone(TelefoneDTO dto, @PathParam("idUsuario") Long idUsuario){
        service.insertTelefone(idUsuario, dto);
        return Response.noContent().build();
    }


    @PATCH
    @Transactional
    @Path("/atualiza-telefone/{id}/{idTelefone}")
    public Response updateTelefone(TelefoneDTO dto, @PathParam("id") Long id, @PathParam("idTelefone") Long idTelefone){
        service.updateTelefone(id, idTelefone, dto);
        return Response.noContent().build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(UsuarioDTO dto, @PathParam("id") Long id){
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/deleta-telefone/{id}/{idTelefone}")
    public Response deleteTelefone(@PathParam("id") Long id, @PathParam("idTelefone") Long idTelefone){
        service.deleteTelefone(id, idTelefone);
        return Response.noContent().build();
    }


    @GET
    public Response findByAll(){
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/telefone/{id}")
    public Response findTelById(@PathParam("id") Long id){
        return Response.ok(service.findTelById(id)).build();
    }

    @GET
    @Path("/telefone/{codigoArea}")
    public Response findTelByCodigoArea(@PathParam("codigoArea") String codigoArea){
        return Response.ok(service.findTelByCodigoArea(codigoArea)).build();
    }
}
