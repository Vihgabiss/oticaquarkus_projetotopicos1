package br.unitins.topicos1.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.OculosResponseDTO;
import br.unitins.topicos1.dto.SenhaDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
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

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUsuarioLogado() {
        LOG.info("Pegando e-mail do usuário logado.");
        String email = jwt.getSubject();
        
        LOG.info("Retornando os dados do usuário logado.");
        return Response.ok(usuarioService.findByEmail(email)).build();
    }

    @PATCH
    @Path("/altera/senha/")
    @RolesAllowed({ "User", "Admin" })
    public Response updateSenha(SenhaDTO dto) {
        LOG.info("Atualizando a senha do usuário.");
        usuarioService.updateSenha(dto);

        LOG.info("Finalizando a atualização da senha.");
        return Response.noContent().build();

    }

    @PATCH
    @Path("/altera/nome/{nome}")
    @RolesAllowed({"User", "Admin"})
    public Response updateNomeUsuario(@PathParam("nome") String nome){
        LOG.infof("Atualizando o nome do usuário para %s", nome);
        usuarioService.updateNomeUsuarioLogado(nome);
        
        LOG.info("Finalizando a atualização do nome.");
        return Response.noContent().build();
    }

    @PATCH
    @Path("/insere/telefone")
    @RolesAllowed({"User", "Admin"})
    public Response insertTelefoneUsuario(TelefoneDTO dto){
        LOG.info("Inserindo o telefone.");
        usuarioService.insertTelefoneUsuarioLogado(dto);

        LOG.info("Finalizando o insert de telefone.");
        return Response.noContent().build();
    }

    @PATCH
    @Path("/update/telefone/{idTelefone}")
    @RolesAllowed({"User", "Admin"})
    public Response updateTelefoneUsuario(@PathParam("idTelefone") Long idTelefone, TelefoneDTO dto){
        LOG.infof("Atualizando o telefone %s", idTelefone);
        usuarioService.updateTelefoneUsuarioLogado(idTelefone, dto);

        LOG.info("Finalizando a atualização do telefone.");
        return Response.noContent().build();
    }

    /*@PATCH
    @Path("/insere/endereco")
    @RolesAllowed({"User", "Admin"})
    public Response insertEnderecoUsuario(EnderecoDTO dto){
         LOG.info("Inserindo o endereço.");
        usuarioService.insertEnderecoUsuarioLogado(dto);

         LOG.info("Finalizando o insert de endereço.");
        return Response.noContent().build();
    }*/

    @PATCH
    @Path("/upload/imagem/{oculosId}")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImagemOculos(@MultipartForm OculosImageForm form, @PathParam("oculosId") Long oculosId) {
        try {
            LOG.info("Salvando a imagem.");
            String nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
            
            LOG.info("Atualizando a nova imagem.");
            OculosResponseDTO oculosDTO = oculosService.updateNomeImagem(oculosId, nomeImagem);
            
            LOG.info("Retornando a imagem.");
            return Response.ok(oculosDTO).build();
        } catch (IOException e) {
            e.printStackTrace();

            LOG.info("Retornando um erro do servidor.");
            Error error = new Error("500", "Erro ao processar a imagem.");
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @PATCH
    @Path("/upload/novaImagem/{oculosId}")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadNovaImagemOculos(@MultipartForm OculosImageForm form, @PathParam("oculosId") Long oculosId) {
        try {
            LOG.info("Deletando a imagem atual.");
            String nomeImagemAtual = oculosService.findById(oculosId).nomeImagem();
            if (nomeImagemAtual != null && !nomeImagemAtual.isBlank()) {
                fileService.excluir(nomeImagemAtual);
            }
    
            LOG.info("Salvando a nova imagem.");
            String nomeImagemNovo = fileService.salvar(form.getNomeImagem(), form.getImagem());
    
            LOG.info("Atualizando a nova imagem.");
            oculosService.updateNomeImagem(oculosId, nomeImagemNovo);
    
            LOG.info("Retornando a imagem atualizada.");
            OculosResponseDTO oculosDTO = oculosService.findById(oculosId);
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