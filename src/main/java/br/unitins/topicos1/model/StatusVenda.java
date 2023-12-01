package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusVenda {

    AGUARDANDO_PAGAMENTO(1, "Aguardando pagamento"),
    PAGAMENTO_CONFIRMADO(2, "Pagamento confirmado"),
    SEPARANDO_PEDIDO(3, "Seprando pedido"),
    EM_TRANSITO(4, "Em trânsito"),
    SAIU_PARA_ENTREGA(5, "Saiu para entrega"),
    ENTREGUE(6, "Entregue"),
    PEDIDO_CANCELADO(7, "Pedido cancelado"),
    PAGAMENTO_NAO_EFETUADO(8, "Pagamento não efetuado");

    private final Integer id;
    private final String label;

    StatusVenda(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusVenda valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (StatusVenda statusVenda : StatusVenda.values()) {
            if (statusVenda.getId().equals(id))
                return statusVenda;
        }

        throw new IllegalArgumentException("Id inválida" + id);
    }
}
