package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.service.EstadoService;
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

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    @POST
    //@RolesAllowed({"Admin"})
    public Response insert(EstadoDTO dto){
        LOG.info("Inserindo um estado");
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @GET
    public Response findAll(){
        LOG.info("Listando todos os estados");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    //@RolesAllowed({ "User", "Admin" })
    @Path("/sigla/{sigla}")
    public Response findBySigla(@Valid @PathParam("sigla") String sigla){
        LOG.infof("Listando o estado com a sigla %s", sigla);
        return Response.ok(service.findBySigla(sigla)).build();
    }

    @PUT
    @Path("/{id}")
    //@RolesAllowed({"Admin"})
    public Response update(EstadoDTO dto, @PathParam("id") Long id){
        LOG.info("Atualizando estado.");
        service.update(id, dto);

        LOG.info("Finalizando a atualização de estado.");
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id){
        LOG.infof("Deletando estado de id %s", id);
        service.delete(id);

        LOG.info("Estado deletado.");
        return Response.noContent().build();
    
    }

    @GET
    @Path("/nome/{nome}")
    //@RolesAllowed({ "User", "Admin"})
    public Response findByNome(@PathParam("nome") String nome){
        LOG.infof("Listando estados com nome %s", nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @POST
    @Path("/insere-cidade/")
   //@RolesAllowed({"Admin"})
    public Response insertCidade(CidadeDTO dto){
        LOG.info("Inserindo cidade.");
        service.insertCidade(dto);

        LOG.info("Finalizando o insert de cidade.");
        return Response.noContent().build();
    }

    @PUT
    @Path("/atualiza-cidade/{idEstado}/{idCidade}")
    //@RolesAllowed({"Admin"})
    public Response updateCidade(CidadeDTO dto, @PathParam("idEstado") Long idEstado, @PathParam("idCidade") Long idCidade){
        LOG.info("Atualizando cidade.");
        service.updateCidade(idCidade, dto);
        
        LOG.info("Finalizando a atualização de cidade.");
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deleta-cidade/{idCidade}")
    //@RolesAllowed({"Admin"})
    public Response deleteCidade(@PathParam("idCidade") Long idCidade){
        LOG.info("Deletando cidade.");
        service.deleteCidade(idCidade);
        
        LOG.info("Cidade deletada.");
        return Response.noContent().build();
    }

    @GET
    @Path("/allCities")
    //@RolesAllowed({ "User", "Admin"})
    public Response findAllCities(){
        LOG.info("Listando todas as cidades.");
        return Response.ok(service.findAllCities()).build();
    }

}
