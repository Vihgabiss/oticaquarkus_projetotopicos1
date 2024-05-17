package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.FabricanteDTO;
import br.unitins.topicos1.dto.FabricanteResponseDTO;
import br.unitins.topicos1.dto.MarcaDTO;
import br.unitins.topicos1.service.FabricanteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

@Path("/fabricante")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FabricanteResource {

    @Inject
    FabricanteService service;

    private static final Logger LOG = Logger.getLogger(FabricanteResource.class);

    @POST
    @RolesAllowed({ "Admin" })
    @Transactional
    public Response insert(@Valid FabricanteDTO dto) {
        LOG.info("Inserindo fabricante");
        try {
            FabricanteResponseDTO retorno = service.insert(dto);
            return Response.status(201).entity(retorno).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/insere-marca/{idFabricante}")
    @RolesAllowed({ "Admin" })
    public Response insertMarca(MarcaDTO dto, @PathParam("idFabricante") Long idFabricante) {

        LOG.infof("Cadastrando um marca para o usuario %s", idFabricante);
        service.insertMarca(idFabricante, dto);

        LOG.info("Finalizando o cadastro de marca.");
        return Response.noContent().build();
    }

    @PATCH
    @Path("/atualiza-marca/{id}/{idMarca}")
    @RolesAllowed({ "Admin" })
    public Response updateMarca(MarcaDTO dto, @PathParam("id") Long id, @PathParam("idMarca") Long idMarca) {
        LOG.info("Atualizando marca.");
        service.updateMarca(id, idMarca, dto);

        LOG.info("Finalizando a atualização de marca.");
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deleta-marca/{id}/{idMarca}")
    @RolesAllowed({ "Admin" })
    public Response deleteMarca(@PathParam("id") Long id, @PathParam("idMarca") Long idMarca) {
        LOG.infof("Deletando marca %s", idMarca);
        service.deleteMarca(id, idMarca);

        LOG.infof("Marca %s deletado", idMarca);
        return Response.noContent().build();
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response update(FabricanteDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando fabricante");
        try {
            service.update(dto, id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando fabricante");
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
        LOG.info("Buscando fabricante por ID");
        try {
            return Response.ok(service.findById(id)).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    // @RolesAllowed({ "Admin" })
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Iniciando a inserção do fabricante");
        try {
            return Response.ok(service.findByNome(nome)).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    // @RolesAllowed({ "Admin" })
    public Response findByAll() {
        LOG.info("Listando fabricantes");
        List<FabricanteResponseDTO> retorno = service.findByAll();
        return Response.ok(retorno).build();
    }

    @GET
    // @RolesAllowed({ "Admin" })
    @Path("/cnpj/{cnpj}")
    public Response findByCNPJ(@PathParam("cnpj") String cnpj) {
        LOG.info("Buscando fabricante por CNPJ");
        List<FabricanteResponseDTO> retorno = service.findByCNPJ(cnpj);
        return Response.ok(retorno).build();
    }

}
