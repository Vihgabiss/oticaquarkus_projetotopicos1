package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Armacao;

public record ArmacaoResponseDTO(
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

    public static ArmacaoResponseDTO valueOf(Armacao armacao) {
        return new ArmacaoResponseDTO(
                armacao.getId(),
                armacao.getReferencia(),
                armacao.getCor(),
                armacao.getTamanho(),
                armacao.getPrecoCusto(),
                armacao.getPrecoVenda(),
                armacao.getQuantidade(),
                armacao.getFabricante().getNome(),
                armacao.getColecao().getNome(),
                armacao.getTipoAroArmacao().getLabel(),
                armacao.getMaterialArmacao().getLabel(),
                armacao.getNomeImagem(),
                armacao.getEstiloOculos().getNome());
    }
}
