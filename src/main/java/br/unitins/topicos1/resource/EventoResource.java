package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.EventoDTO;
import br.unitins.topicos1.dto.EventoResponseDTO;
import br.unitins.topicos1.service.EventoService;
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

@Path("/evento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoResource {

    @Inject
    EventoService service;

    private static final Logger LOG = Logger.getLogger(EventoResource.class);

    @POST
    // @RolesAllowed({ "Admin" })
    @Transactional
    public Response insert(@Valid EventoDTO dto) {
        LOG.info("Inserindo evento");
        EventoResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    // @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response update(@Valid EventoDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando evento");
        EventoResponseDTO retorno = service.update(dto, id);
        return Response.status(Status.OK).entity(retorno).build();
    }

    @DELETE
    // @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando evento");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    // @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando evento por ID");
        EventoResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    // @RolesAllowed({ "Admin" })
    @Path("/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao) {
        LOG.info("Buscando evento por descricao");
        List<EventoResponseDTO> retorno = service.findByDescricao(descricao);
        return Response.ok(retorno).build();
    }

    @GET
    // @RolesAllowed({ "Admin", "User" })
    @Path("/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando evento por nome");
        List<EventoResponseDTO> retorno = service.findByNome(nome);
        return Response.ok(retorno).build();
    }

    @GET
    // @RolesAllowed({ "Admin" })
    public Response findByAll() {
        LOG.info("Listando todos os evento");
        List<EventoResponseDTO> retorno = service.findByAll();
        return Response.ok(retorno).build();
    }
}