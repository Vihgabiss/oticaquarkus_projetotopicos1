package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/endereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {
    
    @Inject
    EnderecoService service;

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
    @Path("/cep/{cep}")
    public Response findByCep(@Valid @PathParam("cep") String cep){
        return Response.ok(service.findByCep(cep)).build();
    }

    @POST
    @Path("/insere-endereco/{idUsuario}")
    public Response insert(@Valid @PathParam("idUsuario") Long idUsuario, EnderecoDTO dto){
        return Response.status(Status.CREATED).entity(service.insert(idUsuario, dto)).build();
    }

    @PUT
    @Transactional
    @Path("/atualiza-endereco/{id}/{idEndereco}")
    public Response update(@Valid EnderecoDTO dto, @PathParam("id") Long id,  @PathParam("idEndereco") Long idEndereco){
        service.update(idEndereco, id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/deleta-endereco/{id}/{idEndereco}")
    public Response delete(@PathParam("id") Long id, @PathParam("idEndereco") Long idEndereco){
        service.delete(id, idEndereco);
        return Response.noContent().build();
    }
}
