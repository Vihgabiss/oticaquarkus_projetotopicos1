package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MaterialArmacao {
    METAL(1, "Metal"),
    ACETATO(2, "Acetato"),
    TITANIO(3, "Titânio");

    private final Integer id;
    private final String label;

    MaterialArmacao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static MaterialArmacao valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (MaterialArmacao materialArmacao : MaterialArmacao.values()) {
            if (materialArmacao.getId().equals(id))
                return materialArmacao;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}