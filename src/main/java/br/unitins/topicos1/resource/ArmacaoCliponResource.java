package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.ArmacaoCliponDTO;
import br.unitins.topicos1.dto.ArmacaoCliponResponseDTO;
import br.unitins.topicos1.form.ArmacaoImageForm;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.service.ArmacaoCliponService;
import br.unitins.topicos1.service.ArmacaoFileService;
import br.unitins.topicos1.service.ArmacaoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/ArmacaoClipon")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArmacaoCliponResource {

    @Inject
    ArmacaoCliponService service;

    @Inject
    ArmacaoFileService fileService;

    @Inject
    ArmacaoService armacaoService;

    @Inject
    ArmacaoRepository armacaoRepository;

    private static final Logger LOG = Logger.getLogger(ArmacaoCliponResource.class);

    @POST
    @RolesAllowed({ "Admin" })
    @Transactional
    public Response insert(@Valid ArmacaoCliponDTO dto) {
        LOG.info("Inserindo Armação Clipon");
        ArmacaoCliponResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response update(@Valid ArmacaoCliponDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando Armação Clipon");
        ArmacaoCliponResponseDTO retorno = service.update(dto, id);
        return Response.status(Status.OK).entity(retorno).build();
    }

    @DELETE
    @RolesAllowed({ "Admin" })
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Armação Clipon");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Armação Clipon por ID");
        ArmacaoCliponResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/referencia/{referencia}")
    public Response findByReferencia(@PathParam("referencia") String referencia) {
        LOG.info("Buscando Armação Clipon por referência");
        List<ArmacaoCliponResponseDTO> retorno = service.findByReferencia(referencia);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin", "User" })
    @Path("/fabricante/{fabricante}")
    public Response findByFabricante(@PathParam("fabricante") String fabricante) {
        LOG.info("Buscando Armação Clipon por fabricante");
        List<ArmacaoCliponResponseDTO> retorno = service.findByFabricante(fabricante);
        return Response.ok(retorno).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findByAll() {
        LOG.info("Listando todos as armações clipon");
        List<ArmacaoCliponResponseDTO> retorno = service.findByAll();
        return Response.ok(retorno).build();
    }

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("100") int pageSize) {

        return Response.ok(service.getAll(page, pageSize)).build();
    }

    @PATCH
    @Path("/imagem/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ArmacaoImageForm form) {
        LOG.info("nome imagem: " + form.getNomeImagem());
        System.out.println("nome imagem: " + form.getNomeImagem());

        fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/imagem/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        System.out.println(nomeImagem);
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }
}
