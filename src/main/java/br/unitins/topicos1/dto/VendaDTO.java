package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record VendaDTO(
                @NotNull(message = "A lista de itens não pode ser nula") List<ItemVendaDTO> itens,
                String cupom, // Código do cupom de desconto (opcional)
                @NotNull(message = "O ID do endereço de entrega não pode ser nulo") Long idEnderecoEntrega,
                @NotNull(message = "O pagamento não pode ser nulo") PagamentoDTO pagamento // DTO do pagamento
) {
}
