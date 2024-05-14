package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ArmacaoClipon;

public record ArmacaoCliponResponseDTO(
        Long id,
        String referencia,
        String cor,
        String tamanho,
        Double precoCusto,
        Double precoVenda,
        Integer quantidade,
        String nomeFabricante,
        String nomeColecao,
        String tipoAroArmacao,
        String tipoArmacao,
        String materialArmacao,
        String nomeImagem,
        String estiloOculos,
        String tipoLenteSolar) {

    public static ArmacaoCliponResponseDTO valueOf(ArmacaoClipon armacaoClipon) {
        return new ArmacaoCliponResponseDTO(
                armacaoClipon.getId(),
                armacaoClipon.getReferencia(),
                armacaoClipon.getCor(),
                armacaoClipon.getTamanho(),
                armacaoClipon.getPrecoCusto(),
                armacaoClipon.getPrecoVenda(),
                armacaoClipon.getQuantidade(),
                armacaoClipon.getFabricante().getNome(),
                armacaoClipon.getColecao().getNome(),
                armacaoClipon.getTipoAroArmacao().getLabel(),
                armacaoClipon.getTipoArmacao().getLabel(),
                armacaoClipon.getMaterialArmacao().getLabel(),
                armacaoClipon.getNomeImagem(),
                armacaoClipon.getEstiloOculos().getNome(),
                armacaoClipon.getTipoLenteSolar().getLabel());
    }
}
