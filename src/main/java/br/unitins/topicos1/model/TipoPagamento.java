package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPagamento {

    DEBITO(0, "Débito"),
    CREDITO(1, "Crédito"),
    PIX(2, "Pix"),
    BOLETO(3, "Boleto");

    private final Integer id;
    private final String label;

    private TipoPagamento(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoPagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoPagamento tipoPagamento : TipoPagamento.values()) {
            if (tipoPagamento.getId().equals(id))
                return tipoPagamento;
        }

        throw new IllegalArgumentException("Id inválida" + id);
    }
}