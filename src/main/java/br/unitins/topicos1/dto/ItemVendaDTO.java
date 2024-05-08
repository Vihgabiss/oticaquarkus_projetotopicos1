// package br.unitins.topicos1.dto;

// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotNull;

// public record ItemVendaDTO(
//         @NotNull(message = "Quantidade não pode ser nula") 
//         @Min(value = 1, message = "Quantidade deve ser maior ou igual a um") 
//         Integer quantidade,

//         Double preco,

//         @NotNull(message = "O id do produto não pode ser nulo") 
//         Long idProduto) {

// }