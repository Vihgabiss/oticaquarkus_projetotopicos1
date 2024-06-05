package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;


import br.unitins.topicos1.dto.EstiloOculosDTO;
import br.unitins.topicos1.service.EstiloOculosService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/estilo_oculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstiloOculosResource {
    
    @Inject
    EstiloOculosService service;

    private static final Logger LOG = Logger.getLogger(EstiloOculosResource.class);

    @POST
    @Transactional
    @Path("/insere-estilo_oculos")
    public Response insert(EstiloOculosDTO dto){
        LOG.info("Inserindo Estilo Óculos.");
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/atualiza-estilo_oculos/{idEstiloOculos}")
    public Response update(@PathParam("idEstiloOculos") Long idEstiloOculos, EstiloOculosDTO dto){
        LOG.info("Atualizando o Estilo Óculos.");
        service.update(idEstiloOculos, dto);

        LOG.info("Finalizando a atualização do Estilo Óculos.");
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/deleta-estilo_oculos/{idEstiloOculos}")
    public Response delete(@PathParam("idEstiloOculos") Long idEstiloOculos){
        LOG.infof("Deletando Estilo Óculos %s", idEstiloOculos);
        service.delete(idEstiloOculos);

        LOG.info("Estilo Óculos deletado.");
        return Response.noContent().build();
    }

    @GET
    public Response findByAll(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("pageSize") @DefaultValue("100") int pageSize){

        LOG.info("Listando todos os estilos de óculos.");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }

    
    @GET
    @Path("/count")
    public long count(){
        return service.count();
    }

    @GET
    @Path("/countById/{id}")
    public long countById(@PathParam("id") Long id){
        return service.countById(id);
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Listando o estilo óculos do id %s", id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/list_id/{id}")
    public Response findByIdList(
        @PathParam("id") Long id,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        LOG.infof("Listando o estilo óculos do id %s", id);
        return Response.ok(service.findByIdList(id, page, pageSize)).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        LOG.infof("Listando o estilo óculos do nome %s", nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        LOG.infof("Listando o estilo óculos com a descrição %s", descricao);
        return Response.ok(service.findByDescricao(descricao)).build();
    }

    @GET
    @Path("/search/all_fields/{termo}")
    public Response findInAllFields(@PathParam("termo") String termo){
        LOG.infof("Listando o(s) estilo óculos com o termo %s", termo);
        return Response.ok(service.findInAllFields(termo)).build();
    }
}
