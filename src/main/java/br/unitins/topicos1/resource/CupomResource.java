package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import br.unitins.topicos1.dto.CupomDTO;
import br.unitins.topicos1.service.CupomService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cupom")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CupomResource {
    
    @Inject
    CupomService service;

    private static final Logger LOG = Logger.getLogger(CupomResource.class);

    @POST
    @Path("/insere-cupom")
    public Response insert(CupomDTO dto){
        LOG.info("Inserindo cupom.");
        service.insert(dto);

        LOG.info("Finalizando o insert de cupom.");
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(CupomDTO dto, @PathParam("id") Long id){
        LOG.info("Atualizando cupom.");
        service.update(id, dto);

        LOG.info("Finalizando a atualização de cupom.");
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.infof("Deletando cupom de id %s", id);
        service.delete(id);

        LOG.info("Cupom deletado.");
        return Response.noContent().build();
    
    }

    @GET
    public Response findAll(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("pageSize") @DefaultValue("100") int pageSize
    ){
        LOG.info("Listando todas os cupons.");
        return Response.ok(service.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }
}
