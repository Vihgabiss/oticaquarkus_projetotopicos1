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
        // Ajuste para acessar os atributos herdados de ArmacaoClipon e Armacao
        return new ArmacaoCliponResponseDTO(
                armacaoClipon.getId(),
                armacaoClipon.getReferencia(), // Herdado de Armacao
                armacaoClipon.getCor(), // Herdado de Armacao
                armacaoClipon.getTamanho(), // Herdado de Armacao
                armacaoClipon.getPrecoCusto(), // Herdado de Armacao
                armacaoClipon.getPrecoVenda(), // Herdado de Armacao
                armacaoClipon.getQuantidade(), // Herdado de Armacao
                armacaoClipon.getFabricante().getNome(), // Herdado de Armacao
                armacaoClipon.getColecao().getNome(), // Herdado de Armacao
                armacaoClipon.getTipoAroArmacao().getLabel(), // Herdado de Armacao
                armacaoClipon.getTipoArmacao().getLabel(), // Herdado de Armacao
                armacaoClipon.getMaterialArmacao().getLabel(), // Herdado de Armacao
                armacaoClipon.getNomeImagem(), // Herdado de Armacao
                armacaoClipon.getEstiloOculos().getNome(), // Herdado de Armacao
                armacaoClipon.getTipoLenteSolar().getLabel()); // Novo atributo em ArmacaoClipon
    }

}