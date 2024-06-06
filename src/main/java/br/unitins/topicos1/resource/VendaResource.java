package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.BoletoDTO;
import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.CartaoDebitoDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.TipoPagamentoDTO;
import br.unitins.topicos1.dto.VendaDTO;
import br.unitins.topicos1.dto.VendaResponseDTO;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.model.Venda;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.TipoPagamentoRepository;
import br.unitins.topicos1.repository.VendaRepository;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.service.VendaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/venda") // Define o caminho base para os endpoints da API de venda
@Produces(MediaType.APPLICATION_JSON) // Indica que os endpoints produzem dados em formato JSON
@Consumes(MediaType.APPLICATION_JSON) // Indica que os endpoints consomem dados em formato JSON
public class VendaResource {

    // Injeção de dependências para os serviços, token JWT e validador
    @Inject
    VendaService service;

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @Inject
    Validator validator;

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    VendaRepository vendaRepository;

    @Inject
    TipoPagamentoRepository tipoPagamentoRepository;

    // Logger para registrar informações e erros
    private static final Logger LOG = Logger.getLogger(VendaResource.class);

    // Endpoint para criar uma nova venda
    @POST
    @RolesAllowed({ "User", "Admin" }) // Restringe o acesso apenas para usuários autenticados com as roles "User" ou
                                       // "Admin"
    public Response insert(@Valid VendaDTO dto) { // O objeto VendaDTO será validado automaticamente
        LOG.info("Inserindo nova venda: " + dto); // Registra a tentativa de inserção de venda
        try {
            String email = jwt.getSubject(); // Obtém o email do usuário autenticado a partir do token JWT
            VendaResponseDTO retorno = service.insert(dto, email); // Chama o serviço para inserir a venda
            return Response.status(Status.CREATED).entity(retorno).build(); // Retorna a venda criada com status 201
        } catch (Exception e) {
            LOG.error("Erro ao inserir venda: " + e.getMessage()); // Registra o erro caso ocorra uma exceção
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build(); // Retorna um erro 400
        }
    }

    // Endpoint para listar todas as vendas (apenas para Admin)
    @GET
    @RolesAllowed({ "Admin" })
    public Response findAll() {
        LOG.info("Listando todas as vendas"); // Registra a tentativa de listar todas as vendas
        return Response.ok(service.findByAll()).build(); // Retorna a lista de vendas
    }

    // Endpoint para buscar uma venda pelo ID
    @GET
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response findById(@PathParam("id") Long id) { // Obtém o ID da venda a partir da URL
        LOG.info("Buscando venda por ID: " + id); // Registra a busca por ID
        Venda venda = vendaRepository.findById(id);
        if (venda == null) {
            return Response.status(Status.NOT_FOUND).build(); // Retorna 404 se não encontrar
        }
        return Response.ok(venda).build(); // Retorna a venda encontrada, ou null caso não encontre
    }

    // Endpoint para listar as vendas do usuário logado
    @GET
    @Path("/minhas-vendas")
    @RolesAllowed({ "User", "Admin" })
    public Response findAllByUserLogged() {
        LOG.info("Buscando vendas do usuário logado"); // Registra a busca pelas vendas do usuário
        String email = jwt.getSubject();
        return Response.ok(service.findByAll(email)).build(); // Retorna a lista de vendas do usuário
    }

    // Endpoint para buscar os itens de uma venda pelo ID
    @GET
    @Path("/{id}/itens")
    @RolesAllowed({ "User", "Admin" })
    public Response findItensByVendaId(@PathParam("id") Long id) { // Obtém o ID da venda a partir da URL
        LOG.info("Buscando itens da venda com ID: " + id); // Registra a busca pelos itens da venda
        try {
            VendaResponseDTO venda = service.findById(id);
            if (venda != null) {
                return Response.ok(venda.itens()).build(); // Retorna os itens da venda
            } else {
                LOG.warn("Venda não encontrada com ID: " + id);
                return Response.status(Status.NOT_FOUND).entity("Venda não encontrada").build(); // Erro 404
            }
        } catch (Exception e) {
            LOG.error("Erro ao buscar itens da venda: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build(); // Erro 400
        }
    }

    // Endpoint para alterar o status de uma venda (apenas para Admin)
    @PUT
    @Path("/{id}/status/{novoStatusId}")
    @RolesAllowed("Admin")
    public Response editStatusVenda(@PathParam("id") Long id, @PathParam("novoStatusId") Integer novoStatusId) {
        LOG.info("Alterando status da venda com ID: " + id + " para o status: " + novoStatusId);
        try {
            VendaResponseDTO retorno = service.editStatusVenda(id, novoStatusId);
            return Response.ok(retorno).build();
        } catch (RuntimeException e) {
            LOG.error("Erro ao alterar status da venda: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Endpoint para realizar pagamento (independente do tipo)
    @POST
    @Path("/{id}/pagamento/boleto")
    @RolesAllowed("Admin, User")
    public Response realizarPagamentoBoleto(@PathParam("id") Long vendaId, @Valid @RequestBody BoletoDTO boletoDTO) {
        LOG.info("Realizando pagamento com boleto para a venda: " + vendaId);
        try {
            TipoPagamento tipoPagamento = tipoPagamentoRepository.findByNome("Boleto");
            if (tipoPagamento == null) {
                throw new RuntimeException("Tipo de pagamento 'Boleto' não encontrado.");
            }
            VendaResponseDTO vendaAtualizada = service.realizarPagamentoBoleto(vendaId, boletoDTO, tipoPagamento);
            return Response.ok(vendaAtualizada).build();
        } catch (RuntimeException e) {
            LOG.error("Erro ao realizar pagamento com boleto: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/pix")
    @RolesAllowed("Admin, User")
    public Response realizarPagamentoPix(@PathParam("id") Long vendaId, @Valid @RequestBody PixDTO pixDTO) {
        LOG.info("Realizando pagamento com Pix para a venda: " + vendaId);
        try {
            TipoPagamento tipoPagamento = tipoPagamentoRepository.findByNome("Pix");
            if (tipoPagamento == null) {
                throw new RuntimeException("Tipo de pagamento 'Pix' não encontrado.");
            }
            VendaResponseDTO vendaAtualizada = service.realizarPagamentoPix(vendaId, pixDTO, tipoPagamento);
            return Response.ok(vendaAtualizada).build();
        } catch (RuntimeException e) {
            LOG.error("Erro ao realizar pagamento com Pix: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/cartao-credito")
    @RolesAllowed("Admin, User")
    public Response realizarPagamentoCartaoCredito(@PathParam("id") Long vendaId,
            @Valid @RequestBody CartaoCreditoDTO cartaoCreditoDTO) {
        LOG.info("Realizando pagamento com cartão de crédito para a venda: " + vendaId);
        try {
            TipoPagamento tipoPagamento = tipoPagamentoRepository.findByNome("Cartão de Crédito");
            if (tipoPagamento == null) {
                throw new RuntimeException("Tipo de pagamento 'Cartão de Crédito' não encontrado.");
            }
            VendaResponseDTO vendaAtualizada = service.realizarPagamentoCartaoCredito(vendaId, cartaoCreditoDTO,
                    tipoPagamento);
            return Response.ok(vendaAtualizada).build();
        } catch (RuntimeException e) {
            LOG.error("Erro ao realizar pagamento com cartão de crédito: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/pagamento/cartao-debito")
    @RolesAllowed("Admin, User")
    public Response realizarPagamentoCartaoDebito(@PathParam("id") Long vendaId,
            @Valid @RequestBody CartaoDebitoDTO cartaoDebitoDTO) {
        LOG.info("Realizando pagamento com cartão de débito para a venda: " + vendaId);
        try {
            TipoPagamento tipoPagamento = tipoPagamentoRepository.findByNome("Cartão de Débito");
            if (tipoPagamento == null) {
                throw new RuntimeException("Tipo de pagamento 'Cartão de Débito' não encontrado.");
            }
            VendaResponseDTO vendaAtualizada = service.realizarPagamentoCartaoDebito(vendaId, cartaoDebitoDTO,
                    tipoPagamento);
            return Response.ok(vendaAtualizada).build();
        } catch (RuntimeException e) {
            LOG.error("Erro ao realizar pagamento com cartão de débito: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/tipos-pagamento")
    @RolesAllowed({ "User", "Admin" })
    public Response getTipoPagamentos() {
        LOG.info("Listando todos os tipos de pagamento");
        try {
            List<TipoPagamentoDTO> tiposPagamento = service.getTipoPagamentos();
            return Response.ok(tiposPagamento).build();
        } catch (Exception e) {
            LOG.error("Erro ao listar tipos de pagamento: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}