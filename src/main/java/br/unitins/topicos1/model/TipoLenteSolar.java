package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoLenteSolar {
    POLARIZADA(1,"Polarizada"),
    ESPELHADA(2,"Espelhada"),
    FOTOCROMATICA(3, "Fotocromática"),
    COLORIDA(4, "Colorida");

    private final Integer id;
    private final String label;

    TipoLenteSolar(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoLenteSolar valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoLenteSolar tipoLenteSolar : TipoLenteSolar.values()) {
            if (tipoLenteSolar.getId().equals(id))
                return tipoLenteSolar;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}