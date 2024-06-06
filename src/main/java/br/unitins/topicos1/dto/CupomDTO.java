package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record CupomDTO(
        @NotBlank(message = "O campo nome deve ser preenchido.") String nome,
        String descricao,
        LocalDate dataLancamento,
        Long idEvento) {

}
