package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.OculosDTO;
import br.unitins.topicos1.dto.OculosResponseDTO;
import br.unitins.topicos1.service.OculosService;
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

@Path("/oculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OculosResource {

    @Inject
    OculosService service;

    private static final Logger LOG = Logger.getLogger(OculosResource.class);

    @POST
    @RolesAllowed({ "Admin" })
    @Transactional
    public Response insert(@Valid OculosDTO dto) {
        LOG.info("Inserindo óculos");
        OculosResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response update(@Valid OculosDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando óculos");
        OculosResponseDTO retorno = service.update(dto, id);
        return Response.status(Status.OK).entity(retorno).build();
    }

    @DELETE
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando óculos");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando óculos por ID");
        OculosResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/referencia/{referencia}")
    public Response findByReferencia(@PathParam("referencia") String referencia) {
        LOG.info("Buscando óculos por referência");
        List<OculosResponseDTO> retorno = service.findByReferencia(referencia);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin", "User" })
    @Path("/marca/{marca}")
    public Response findByMarca(@PathParam("marca") String marca) {
        LOG.info("Buscando óculos por marca");
        List<OculosResponseDTO> retorno = service.findByMarca(marca);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin", "User" })
    @Path("/tipoOculos/{idTipoOculos}")
    public Response findByTipoOculos(@PathParam("idTipoOculos") Integer idTipoOculos) {
        LOG.info("Buscando óculos por tipo de óculos");
        List<OculosResponseDTO> oculosList = service.findByTipoOculos(idTipoOculos);
        return Response.ok(oculosList).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findByAll() {
        LOG.info("Listando todos os óculos");
        List<OculosResponseDTO> retorno = service.findByAll();
        return Response.ok(retorno).build();
    }
}
