package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoAroArmacao {
    ARO_FECHADO(1, "Aro fechado"),
    ARO_ABERTO(2, "Aro aberto"),
    SEM_ARO(3, "Sem aro");

    private final Integer id;
    private final String label;

    TipoAroArmacao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoAroArmacao valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoAroArmacao tipoAroArmacao : TipoAroArmacao.values()) {
            if (tipoAroArmacao.getId().equals(id))
                return tipoAroArmacao;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}