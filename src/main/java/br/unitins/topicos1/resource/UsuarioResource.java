package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @POST
    // @RolesAllowed({ "User", "Admin"})
    public Response insert(UsuarioDTO dto) throws Exception {
        LOG.info("Cadastrando um usuario.");
        LOG.info(dto.idPerfil());
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PATCH
    @Path("/insere-telefone/{idUsuario}")
    @RolesAllowed({ "Admin" })
    public Response insertTelefone(TelefoneDTO dto, @PathParam("idUsuario") Long idUsuario) {

        LOG.infof("Cadastrando um telefone para o usuario %s", idUsuario);
        service.insertTelefone(idUsuario, dto);

        LOG.info("Finalizando o cadastro de telefone.");
        return Response.noContent().build();
    }

    @PATCH
    @Path("/atualiza-telefone/{id}/{idTelefone}")
    @RolesAllowed({ "Admin" })
    public Response updateTelefone(TelefoneDTO dto, @PathParam("id") Long id,
            @PathParam("idTelefone") Long idTelefone) {
        LOG.info("Atualizando telefone.");
        service.updateTelefone(id, idTelefone, dto);

        LOG.info("Finalizando a atualização de telefone.");
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        LOG.infof("Atualizando os dados do usuario %s", id);
        service.update(dto, id);

        LOG.infof("Finalizando a atualização dos dados do usuario %s", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando usuario %s", id);
        service.delete(id);

        LOG.infof("Usuario %s deletado", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/deleta-telefone/{id}/{idTelefone}")
    @RolesAllowed({ "Admin" })
    public Response deleteTelefone(@PathParam("id") Long id, @PathParam("idTelefone") Long idTelefone) {
        LOG.infof("Deletando telefone %s", idTelefone);
        service.deleteTelefone(id, idTelefone);

        LOG.infof("Telefone %s deletado", idTelefone);
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({ "Admin", "User" })
    public Response findByAll() {
        LOG.info("Listando todos os usuarios.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Listando o usuario de id %s", id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({ "Admin" })
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("Listando os usuarios com nome %s", nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/telefone/{id}")
    @RolesAllowed({ "Admin" })
    public Response findTelById(@PathParam("id") Long id) {
        LOG.infof("Listando telefone de id %s", id);
        return Response.ok(service.findTelById(id)).build();
    }

    @GET
    @Path("/telefone/{codigoArea}")
    @RolesAllowed({ "Admin" })
    public Response findTelByCodigoArea(@PathParam("codigoArea") String codigoArea) {
        LOG.infof("Listando todos os telefones de codigo de area %s", codigoArea);
        return Response.ok(service.findTelByCodigoArea(codigoArea)).build();
    }

    @GET
    @Path("/{idUsuario}/telefones")
    //@RolesAllowed({"Admin"})
    public Response findByIdUsuario(@PathParam("idUsuario") Long idUsuario){
        LOG.infof("Listando os telefones do usuario %s", idUsuario);
        return Response.ok(service.findByIdUsuario(idUsuario)).build();
    }

    @POST
    @Path("/cadastrar")
    public Response cadastroProprio(UsuarioDTO dto) {
        LOG.info("Criando um cadastro de usuário no sistema.");
        return Response.status(Status.CREATED).entity(service.cadastroProprio(dto)).build();
    }
}
