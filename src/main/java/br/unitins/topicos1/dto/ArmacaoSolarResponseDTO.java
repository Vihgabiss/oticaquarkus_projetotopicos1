package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ArmacaoSolar;

public record ArmacaoSolarResponseDTO(
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
        String estiloOculos,
        String lenteSolar) {

    public static ArmacaoSolarResponseDTO valueOf(ArmacaoSolar armacaoSolar) {
        return new ArmacaoSolarResponseDTO(
                armacaoSolar.getId(),
                armacaoSolar.getReferencia(),
                armacaoSolar.getCor(),
                armacaoSolar.getTamanho(),
                armacaoSolar.getPrecoCusto(),
                armacaoSolar.getPrecoVenda(),
                armacaoSolar.getQuantidade(),
                armacaoSolar.getFabricante().getNome(),
                armacaoSolar.getColecao().getNome(),
                armacaoSolar.getTipoAroArmacao().getLabel(),
                armacaoSolar.getMaterialArmacao().getLabel(),
                armacaoSolar.getNomeImagem(),
                armacaoSolar.getEstiloOculos().getNome(),
                armacaoSolar.getTipoLenteSolar().getLabel());
    }
}
