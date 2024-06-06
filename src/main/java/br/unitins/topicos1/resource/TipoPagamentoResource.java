package br.unitins.topicos1.resource;

import java.util.Set;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.TipoPagamentoDTO;
import br.unitins.topicos1.service.TipoPagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/tipos-pagamento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoPagamentoResource {

    private static final Logger LOG = Logger.getLogger(TipoPagamentoResource.class);

    @Inject
    TipoPagamentoService service;

    @Inject
    Validator validator;

    // CREATE
    @POST
    @Transactional
    @RolesAllowed("Admin")
    public Response create(@Valid TipoPagamentoDTO tipoPagamentoDTO) {
        LOG.info("Criando novo tipo de pagamento: " + tipoPagamentoDTO);
        try {
            return Response.status(Status.CREATED).entity(service.create(tipoPagamentoDTO)).build();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            LOG.error("Erro de validação ao criar tipo de pagamento: " + violations);
            return Response.status(Status.BAD_REQUEST).entity(violations).build();
        } catch (Exception e) {
            LOG.error("Erro ao criar tipo de pagamento: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // READ
    @GET

    public Response getAll() {
        LOG.info("Listando todos os tipos de pagamento");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response getById(@PathParam("id") Long id) {
        LOG.info("Buscando tipo de pagamento por ID: " + id);
        try {
            return Response.ok(service.getById(id)).build();
        } catch (NotFoundException e) {
            LOG.warn("Tipo de pagamento não encontrado com o ID: " + id);
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    // UPDATE
    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, @Valid TipoPagamentoDTO tipoPagamentoDTO) {
        LOG.info("Atualizando tipo de pagamento com ID: " + id);
        try {
            return Response.ok(service.update(id, tipoPagamentoDTO)).build();
        } catch (NotFoundException e) {
            LOG.warn("Tipo de pagamento não encontrado com o ID: " + id);
            return Response.status(Status.NOT_FOUND).build();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            LOG.error("Erro de validação ao atualizar tipo de pagamento: " + violations);
            return Response.status(Status.BAD_REQUEST).entity(violations).build();
        } catch (Exception e) {
            LOG.error("Erro ao atualizar tipo de pagamento: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Excluindo tipo de pagamento com ID: " + id);
        try {
            service.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.warn("Tipo de pagamento não encontrado com o ID: " + id);
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}