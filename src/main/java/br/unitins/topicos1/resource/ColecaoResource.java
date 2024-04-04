package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.ColecaoDTO;
import br.unitins.topicos1.service.ColecaoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/colecao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColecaoResource {
    
    @Inject
    ColecaoService service;

    private static final Logger LOG = Logger.getLogger(ColecaoResource.class);

    @POST
    @Transactional
    @Path("/insere-colecao")
    public Response insert(ColecaoDTO dto){
        LOG.info("Inserindo Coleção.");
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/atualiza-colecao/{idColecao}")
    public Response update(@PathParam("idColecao") Long idColecao, ColecaoDTO dto){
        LOG.info("Atualizando a Coleção.");
        service.update(idColecao, dto);

        LOG.info("Finalizando a atualização da Coleção.");
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/deleta-colecao/{idColecao}")
    public Response delete(@PathParam("idColecao") Long idColecao){
        LOG.infof("Deletando a Coleção %s", idColecao);
        service.delete(idColecao);

        LOG.info("Coleção deletado.");
        return Response.noContent().build();
    }

    @GET
    public Response findByAll(){
        LOG.info("Listando todos as coleções.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Listando a coleção do id %s", id);
        return Response.ok(service.findById(id)).build();
    }
}
