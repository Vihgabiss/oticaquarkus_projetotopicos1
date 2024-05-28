package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ArmacaoGrau;

public record ArmacaoGrauResponseDTO(
        Long id,
        String referencia,
        String cor,
        String tamanho,
        Double precoCusto,
        Double precoVenda,
        Integer quantidade,
        String nome_fabricante,
        String nome_colecao,
        String tipo_aro_armacao,
        String material_armacao,
        String nomeImagem,
        String estiloOculos) {

    public static ArmacaoGrauResponseDTO valueOf(ArmacaoGrau armacaoGrau) {
        return new ArmacaoGrauResponseDTO(
                armacaoGrau.getId(),
                armacaoGrau.getReferencia(),
                armacaoGrau.getCor(),
                armacaoGrau.getTamanho(),
                armacaoGrau.getPrecoCusto(),
                armacaoGrau.getPrecoVenda(),
                armacaoGrau.getQuantidade(),
                armacaoGrau.getFabricante().getNome(),
                armacaoGrau.getColecao().getNome(),
                armacaoGrau.getTipoAroArmacao().getLabel(),
                armacaoGrau.getMaterialArmacao().getLabel(),
                armacaoGrau.getNomeImagem(),
                armacaoGrau.getEstiloOculos().getNome());
    }
}
