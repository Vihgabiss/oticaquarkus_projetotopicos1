package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.DadosUsuarioLogadoDTO;
import br.unitins.topicos1.dto.SenhaDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.service.ArmacaoFileService;
import br.unitins.topicos1.service.ArmacaoService;
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

        UsuarioResponseDTO usuarioAtualizado = usuarioService.findByEmail(email);

        LOG.info("Retornando os dados do usuário logado.");
        return Response.ok(usuarioAtualizado).build();
    }

    @PATCH
    @Path("/altera/senha")
    @RolesAllowed({ "User", "Admin" })
    public Response updateSenha(SenhaDTO dto) {
        LOG.info("Atualizando a senha do usuário.");
        usuarioService.updateSenha(dto);

        LOG.info("Finalizando a atualização da senha.");
        return Response.noContent().build();

    }

    @PATCH
    @Path("/altera/dados")
    @RolesAllowed({ "User", "Admin" })
    public Response updateNomeUsuario(DadosUsuarioLogadoDTO dto) {
        LOG.infof("Atualizando os dados do usuário");
        usuarioService.updateDadosUsuarioLogado(dto);

        LOG.info("Finalizando a atualização dos dados.");
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

}