package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.service.VendaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/venda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendaResource {

    @Inject
    VendaService service;

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(VendaResource.class);

    @POST
    @RolesAllowed({ "User", "Admin" })
    public Response insert(@Valid VendaDTO dto) {
        try {
            String email = jwt.getSubject();
            VendaResponseDTO retorno = service.insert(dto, email);
            return Response.status(Status.CREATED).entity(retorno).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response findAll() {
        LOG.info("Lista de vendas");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando venda por ID");
        VendaResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @Path("/minhas-vendas")
    @RolesAllowed({ "User", "Admin" })
    public Response findAllByUserLogged() {
        LOG.info("Buscando venda pelo usuário logado");
        String email = jwt.getSubject();
        return Response.ok(service.findByAll(email)).build();
    }

    @GET
    @Path("/{id}/itens")
    @RolesAllowed({ "User", "Admin" })
    public Response findItensByVendaId(@PathParam("id") Long id) {
        LOG.info("Buscando itens da venda pelo ID");
        try {
            VendaResponseDTO venda = service.findById(id);
            if (venda != null) {
                return Response.ok(venda.itens()).build();
            } else {
                return Response.status(Status.NOT_FOUND).entity("Venda não encontrada").build();
            }
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}