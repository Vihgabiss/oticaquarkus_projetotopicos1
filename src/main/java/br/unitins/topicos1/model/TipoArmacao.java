package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoArmacao {
    GRAU(1, "Grau"),
    SOL(2, "Aro aberto"),
    CLIPON(3, "Clipon");

    private final Integer id;
    private final String label;

    TipoArmacao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoArmacao valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoArmacao tipoArmacao : TipoArmacao.values()) {
            if (tipoArmacao.getId().equals(id))
                return tipoArmacao;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}