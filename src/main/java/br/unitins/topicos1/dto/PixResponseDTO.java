package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pix;

public record PixResponseDTO(
    Long id,
    String chavePix,
    String qrCode) {
  public static PixResponseDTO valueOf(Pix pix) {

    return new PixResponseDTO(
        pix.getId(),
        pix.getChavePix(),
        pix.getQrCode());
  }
}
