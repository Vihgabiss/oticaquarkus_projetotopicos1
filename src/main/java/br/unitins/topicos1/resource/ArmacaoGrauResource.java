package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.ArmacaoGrauDTO;
import br.unitins.topicos1.dto.ArmacaoGrauResponseDTO;
import br.unitins.topicos1.service.ArmacaoGrauService;
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

@Path("/ArmacaoGrau")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArmacaoGrauResource {

    @Inject
    ArmacaoGrauService service;

    private static final Logger LOG = Logger.getLogger(ArmacaoGrauResource.class);

    @POST
    @RolesAllowed({ "Admin" })
    @Transactional
    public Response insert(@Valid ArmacaoGrauDTO dto) {
        LOG.info("Inserindo Armação de Grau");
        ArmacaoGrauResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response update(@Valid ArmacaoGrauDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando Armação de Grau");
        ArmacaoGrauResponseDTO retorno = service.update(dto, id);
        return Response.status(Status.OK).entity(retorno).build();
    }

    @DELETE
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Armação de Grau");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Armação de Grau por ID");
        ArmacaoGrauResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/referencia/{referencia}")
    public Response findByReferencia(@PathParam("referencia") String referencia) {
        LOG.info("Buscando Armação de Grau por referência");
        List<ArmacaoGrauResponseDTO> retorno = service.findByReferencia(referencia);
        return Response.ok(retorno).build();
    }

    @GET
    // @RolesAllowed({ "Admin", "User" })
    @Path("/fabricante/{fabricante}")
    public Response findByFabricante(@PathParam("fabricante") String fabricante) {
        LOG.info("Buscando Armação de Grau por fabricante");
        List<ArmacaoGrauResponseDTO> retorno = service.findByFabricante(fabricante);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findByAll() {
        LOG.info("Listando todos as armações de Grau");
        List<ArmacaoGrauResponseDTO> retorno = service.findByAll();
        return Response.ok(retorno).build();
    }
}
