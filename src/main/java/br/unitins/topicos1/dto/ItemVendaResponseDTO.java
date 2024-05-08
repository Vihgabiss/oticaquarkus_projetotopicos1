// package br.unitins.topicos1.dto;

// import java.util.List;

// import br.unitins.topicos1.model.ItemVenda;

// public record ItemVendaResponseDTO(
//         Integer quantidade,
//         Double preco,
//         Long idProduto,
//         String referencia) {
//     public static ItemVendaResponseDTO valueOf(ItemVenda item) {
//         return new ItemVendaResponseDTO(
//                 item.getQuantidade(),
//                 item.getArmacao().getPrecoVenda(),
//                 item.getArmacao().getId(),
//                 item.getArmacao().getReferencia());
//     }

//     public static List<ItemVendaResponseDTO> valueOf(List<ItemVenda> item) {
//         return item.stream().map(i -> ItemVendaResponseDTO.valueOf(i)).toList();
//     }

// }