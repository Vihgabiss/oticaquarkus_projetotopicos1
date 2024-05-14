package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.dto.MarcaResponseDTO;
import br.unitins.topicos1.service.MarcaService;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/marca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaService service;

    private static final Logger LOG = Logger.getLogger(MarcaResource.class);

    @POST
    @RolesAllowed({ "Admin" })
    @Transactional
    public Response insert(@Valid MarcaDTO dto) {
        LOG.info("Inserindo marca");
        try {
            MarcaResponseDTO retorno = service.insert(dto);
            return Response.status(Status.CREATED).entity(retorno).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response update(MarcaDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando marca");
        try {
            service.update(dto, id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando marca");
        try {
            service.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando marca pelo ID");
        try {
            MarcaResponseDTO retorno = service.findById(id);
            return Response.ok(retorno).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({ "Admin" })
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando marca pelo nome");
        try {
            List<MarcaResponseDTO> retorno = service.findByNome(nome);
            return Response.ok(retorno).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findByAll() {
        LOG.info("Listando todas as marcas");
        try {
            List<MarcaResponseDTO> retorno = service.findByAll();
            return Response.ok(retorno).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
