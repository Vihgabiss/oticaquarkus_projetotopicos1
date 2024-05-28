package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.service.EnderecoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/endereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    private static final Logger LOG = Logger.getLogger(EnderecoResource.class);

    @GET
    @RolesAllowed({ "Admin" })
    public Response findByAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("100") int pageSize) {

        LOG.info("Listando todos os endereços.");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return service.count();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Listando o endereço do id %s", id);
        return Response.ok(service.findById(id)).build();
    }
    

    @GET
    @Path("/cep/{cep}")
    @RolesAllowed({ "Admin" })
    public Response findByCep(@PathParam("cep") String cep) {
        LOG.infof("Listando o endereço do cep %s", cep);
        return Response.ok(service.findByCep(cep)).build();
    }

    @GET
    @Path("/idUsuario/{idUsuario}")
    //@RolesAllowed({"Admin"})
    public Response findByIdUsuario(@PathParam("idUsuario") Long idUsuario){
        LOG.infof("Listando o endereço do usuario %s", idUsuario);
        return Response.ok(service.findByIdUsuario(idUsuario)).build();
    }

    @POST
    @Path("/insere-endereco")
    @RolesAllowed({ "Admin" })
    public Response insert(EnderecoDTO dto) {
        LOG.info("Inserindo endereço.");
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/atualiza-endereco/{idEndereco}")
    @RolesAllowed({ "Admin" })
    public Response update(EnderecoDTO dto, @PathParam("idEndereco") Long idEndereco) {
        LOG.info("Atualizando o endereço.");
        service.update(idEndereco, dto);

        LOG.info("Finalizando a atualização do endereço.");
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/deleta-endereco/{idEndereco}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("idEndereco") Long idEndereco) {
        LOG.infof("Deletando endereço %s", idEndereco);
        service.delete(idEndereco);

        LOG.info("Endereço deletado.");
        return Response.noContent().build();
    }
}
