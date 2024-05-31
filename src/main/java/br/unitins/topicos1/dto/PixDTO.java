package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record PixDTO(
                @NotBlank(message = "A chave PIX não pode ser vazia") String chavePix,
                @NotBlank(message = "O QRCode não pode ser vazio") String qrCode

) {
}
