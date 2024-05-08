// package br.unitins.topicos1.service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import br.unitins.topicos1.dto.ItemVendaDTO;
// import br.unitins.topicos1.dto.ItemVendaResponseDTO;
// import br.unitins.topicos1.dto.VendaDTO;
// import br.unitins.topicos1.dto.VendaResponseDTO;
// import br.unitins.topicos1.model.Armacao;
// import br.unitins.topicos1.model.ItemVenda;
// import br.unitins.topicos1.model.StatusVenda;
// import br.unitins.topicos1.model.TipoPagamento;
// import br.unitins.topicos1.model.Venda;
// import br.unitins.topicos1.repository.ArmacaoSolarRepository;
// import br.unitins.topicos1.repository.UsuarioRepository;
// import br.unitins.topicos1.repository.VendaRepository;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.transaction.Transactional;

// @ApplicationScoped
// public class VendaServiceImpl implements VendaService {

//     @Inject
//     ArmacaoSolarRepository armacaoRepository;

//     @Inject
//     UsuarioRepository usuarioRepository;

//     @Inject
//     VendaRepository vendaRepository;

//     @Override
//     @Transactional
//     public VendaResponseDTO insert(VendaDTO dto, String email) {
//         Venda venda = new Venda();

//         venda.setDataHora(LocalDateTime.now());

//         Double total = 0.0;
//         for (ItemVendaDTO itemDto : dto.itens()) {
//             Armacao armacao = armacaoRepository.findById(itemDto.idProduto());
//             total += (armacao.getPrecoVenda() * itemDto.quantidade());
//         }

//         venda.setValorTotal(total);

//         venda.setTipoPagamento(TipoPagamento.valueOf(dto.idTipoPagamento()));

//         venda.setStatusVenda(StatusVenda.valueOf(dto.idStatusVenda()));

//         venda.setItens(new ArrayList<ItemVenda>());

//         for (ItemVendaDTO itemDto : dto.itens()) {
//             ItemVenda item = new ItemVenda();
//             item.setPreco(itemDto.preco());
//             item.setQuantidade(itemDto.quantidade());
//             item.setVenda(venda);

//             Armacao armacao = armacaoRepository.findById(itemDto.idProduto());

//             item.setArmacao(armacao);

//             armacao.setQuantidade(armacao.getQuantidade() - item.getQuantidade());

//             venda.getItens().add(item);
//         }

//         venda.setUsuario(usuarioRepository.findByEmail(email));

//         vendaRepository.persist(venda);

//         return VendaResponseDTO.valueOf(venda);
//     }

//     public List<VendaResponseDTO> findByAll() {
//         return vendaRepository.listAll().stream()
//                 .map(e -> VendaResponseDTO.valueOf(e)).toList();
//     }

//     @Override
//     public List<VendaResponseDTO> findByAll(String email) {
//         return vendaRepository.findAll(email).stream()
//                 .map(e -> VendaResponseDTO.valueOf(e))
//                 .toList();
//     }

//     @Override
//     public VendaResponseDTO findById(Long id) {
//         return VendaResponseDTO.valueOf(vendaRepository.findById(id));
//     }

//     @Override
//     public VendaResponseDTO findItemVendaById(Long id) {
//         Venda venda = vendaRepository.findById(id);

//         if (venda != null) {
//             VendaResponseDTO vendaResponseDTO = VendaResponseDTO.valueOf(venda);

//             vendaResponseDTO.itens(ItemVendaResponseDTO.valueOf(venda.getItens()));

//             return vendaResponseDTO;
//         } else {
//             return null;
//         }
//     }

// }