package br.unitins.topicos1.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.dto.ArmacaoResponseDTO;
import br.unitins.topicos1.dto.SenhaDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.form.ArmacaoImageForm;
import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.service.ArmacaoFileService;
import br.unitins.topicos1.service.ArmacaoService;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
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
    ArmacaoFileService fileService;

    @Inject
    ArmacaoService armacaoService;

    @Inject
    ArmacaoRepository armacaoRepository;

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
    @RolesAllowed({ "User", "Admin" })
    public Response updateNomeUsuario(@PathParam("nome") String nome) {
        LOG.infof("Atualizando o nome do usuário para %s", nome);
        usuarioService.updateNomeUsuarioLogado(nome);

        LOG.info("Finalizando a atualização do nome.");
        return Response.noContent().build();
    }

    @PATCH
    @Path("/insere/telefone")
    @RolesAllowed({ "User", "Admin" })
    public Response insertTelefoneUsuario(TelefoneDTO dto) {
        LOG.info("Inserindo o telefone.");
        usuarioService.insertTelefoneUsuarioLogado(dto);

        LOG.info("Finalizando o insert de telefone.");
        return Response.noContent().build();
    }

    @PATCH
    @Path("/update/telefone/{idTelefone}")
    @RolesAllowed({ "User", "Admin" })
    public Response updateTelefoneUsuario(@PathParam("idTelefone") Long idTelefone, TelefoneDTO dto) {
        LOG.infof("Atualizando o telefone %s", idTelefone);
        usuarioService.updateTelefoneUsuarioLogado(idTelefone, dto);

        LOG.info("Finalizando a atualização do telefone.");
        return Response.noContent().build();
    }

    /*
     * @PATCH
     * 
     * @Path("/insere/endereco")
     * 
     * @RolesAllowed({"User", "Admin"})
     * public Response insertEnderecoUsuario(EnderecoDTO dto){
     * LOG.info("Inserindo o endereço.");
     * usuarioService.insertEnderecoUsuarioLogado(dto);
     * 
     * LOG.info("Finalizando o insert de endereço.");
     * return Response.noContent().build();
     * }
     */

    @PATCH
    @Path("/upload/imagem/{armacaoId}")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImagemArmacao(@MultipartForm ArmacaoImageForm form, @PathParam("armacaoId") Long armacaoId) {
        try {
            // 1. Verificar se a armação existe
            Armacao armacao = armacaoRepository.findById(armacaoId);
            if (armacao == null) {
                return Response.status(Status.NOT_FOUND)
                        .entity(new Error("404", "Armação não encontrada")).build();
            }

            // 2. Salvar a imagem (usando o ArmacaoFileService)
            String nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());

            // 3. Atualizar o nome da imagem na armação
            armacao.setNomeImagem(nomeImagem);
            // Converte Armacao para ArmacaoDTO

            // 4. Retornar a armação atualizada (opcional)
            return Response.ok(ArmacaoResponseDTO.valueOf(armacao)).build();

        } catch (EntityNotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(new Error("404", e.getMessage())).build();
        } catch (IOException e) {
            LOG.error("Erro ao processar a imagem: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Error("500", "Erro ao processar a imagem")).build();
        }
    }

    @PATCH
    @Path("/upload/novaImagem/{armacaoId}")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadNovaImagemArmacao(@MultipartForm ArmacaoImageForm form,
            @PathParam("armacaoId") Long armacaoId) {
        try {
            LOG.info("Deletando a imagem atual.");

            // Buscar a armação para obter o nome da imagem atual
            Armacao armacaoAtual = armacaoRepository.findById(armacaoId);
            if (armacaoAtual != null) {
                String nomeImagemAtual = armacaoAtual.getNomeImagem();
                if (nomeImagemAtual != null && !nomeImagemAtual.isBlank()) {
                    fileService.excluir(nomeImagemAtual);
                }
            }

            LOG.info("Salvando a nova imagem.");
            String nomeImagemNovo = fileService.salvar(form.getNomeImagem(), form.getImagem());

            // Buscar novamente a armação (após possível exclusão da imagem antiga)
            Armacao armacao = armacaoRepository.findById(armacaoId);
            if (armacao == null) {
                return Response.status(Status.NOT_FOUND)
                        .entity(new Error("404", "Armação não encontrada")).build();
            }

            // Atualizar o nome da imagem e persistir a alteração
            armacao.setNomeImagem(nomeImagemNovo);
            armacaoRepository.persist(armacao);

            // Converter a entidade Armacao para ArmacaoResponseDTO (usando o método valueOf
            // da subclasse)
            ArmacaoResponseDTO armacaoDTO = ArmacaoResponseDTO.valueOf(armacao);

            LOG.info("Retornando a imagem atualizada.");
            return Response.ok(armacaoDTO).build(); // Retorna o ArmacaoResponseDTO

        } catch (EntityNotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(new Error("404", e.getMessage())).build();
        } catch (IOException e) {
            LOG.error("Erro ao processar a imagem: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Error("500", "Erro ao processar a imagem")).build();
        }
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        File file = fileService.obter(nomeImagem);

        if (file == null) {
            LOG.error("Erro: Imagem não encontrada.");
            Error error = new Error("404", "Imagem não encontrada.");
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }

        try {
            byte[] fileContent = Files.readAllBytes(file.toPath()); // Lê o conteúdo do arquivo em um byte[]
            ResponseBuilder response = Response.ok(fileContent); // Retorna o byte[] no Response
            response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
            return response.build();
        } catch (IOException e) {
            LOG.error("Erro ao ler o arquivo: " + e.getMessage());
            Error error = new Error("500", "Erro ao processar a imagem.");
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

}