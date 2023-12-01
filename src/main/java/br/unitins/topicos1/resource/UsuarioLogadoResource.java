package br.unitins.topicos1.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.dto.OculosResponseDTO;
import br.unitins.topicos1.dto.SenhaDTO;
import br.unitins.topicos1.form.OculosImageForm;
import br.unitins.topicos1.service.OculosFileService;
import br.unitins.topicos1.service.OculosService;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @Inject
    OculosFileService fileService;

    @Inject
    OculosService oculosService;

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUsuarioLogado() {
        String email = jwt.getSubject();
        return Response.ok(usuarioService.findByEmail(email)).build();
    }

    @PATCH
    @Path("/altera/senha/")
    @RolesAllowed({ "User", "Admin" })
    public Response updateSenha(SenhaDTO dto) {
        usuarioService.updateSenha(dto);
        return Response.noContent().build();

    }

    @PATCH
    @Path("/upload/imagem/{oculosId}")
    @RolesAllowed({ "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImagemOculos(@MultipartForm OculosImageForm form, @PathParam("oculosId") Long oculosId) {
        try {
            String nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
            OculosResponseDTO oculosDTO = oculosService.updateNomeImagem(oculosId, nomeImagem);
            return Response.ok(oculosDTO).build();
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("500", "Erro ao processar a imagem.");
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }
}