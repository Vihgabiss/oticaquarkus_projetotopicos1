package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoOculos {
    GRAU(1, "Grau"),
    SOL(2, "Sol"),
    CLIPON(3, "Clipon");

    private final Integer id;
    private final String label;

    TipoOculos(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoOculos valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoOculos tipoOculos : TipoOculos.values()) {
            if (tipoOculos.getId().equals(id))
                return tipoOculos;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}
