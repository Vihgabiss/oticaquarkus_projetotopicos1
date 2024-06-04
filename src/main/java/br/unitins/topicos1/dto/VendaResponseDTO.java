package br.unitins.topicos1.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.model.Cupom;
import br.unitins.topicos1.model.Venda;

public record VendaResponseDTO(
        Long id,
        LocalDateTime dataHora,
        UsuarioResponseDTO usuario,
        Double valorTotal,
        List<ItemVendaResponseDTO> itens,
        EnderecoResponseDTO enderecoEntrega,
        String statusVenda,
        Cupom cupom) { // Adicionei o campo para representar o pagamento

    public static VendaResponseDTO valueOf(Venda venda) {
        return new VendaResponseDTO(
                venda.getId(),
                venda.getDataHora(),
                UsuarioResponseDTO.valueOf(venda.getUsuario()),
                venda.getValorTotal(),
                ItemVendaResponseDTO.valueOf(venda.getItens()),
                EnderecoResponseDTO.valueOf(venda.getEnderecoEntrega()),
                venda.getStatusVenda().getLabel(),
                venda.getCupom()); // Adicionei o campo para representar o pagamento
    }

    public VendaResponseDTO itens(List<ItemVendaResponseDTO> itens) {
        return new VendaResponseDTO(id, dataHora, usuario, valorTotal, itens, enderecoEntrega,statusVenda, cupom);
    }
}
