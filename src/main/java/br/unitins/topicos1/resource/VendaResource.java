package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

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
    @Path("/venda/{email}")
    public Response findAll(@PathParam ("email") String email) {
        return Response.ok(service.findByAll(email)).build();
    }
}
