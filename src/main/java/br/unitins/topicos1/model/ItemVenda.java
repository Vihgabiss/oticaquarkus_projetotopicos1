package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemVenda extends DefaultEntity {

    private Integer quantidade;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "id_oculos")
    private Oculos oculos;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Oculos getOculos() {
        return oculos;
    }

    public void setOculos(Oculos oculos) {
        this.oculos = oculos;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    
}
