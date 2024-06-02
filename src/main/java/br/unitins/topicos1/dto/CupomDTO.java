package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CupomDTO(
                @NotBlank(message = "O campo nome deve ser preenchido.") String nome,
                String descricao,
                LocalDate dataLancamento,
                Long idEvento,
                @Max(value = 80, message = "A porcentagem máxima do desconto é 80%.") 
                @Min(value = 10, message = "A porcentagem mínima do desconto é 10%.") 
                Integer porcentagemDesconto) {

}
