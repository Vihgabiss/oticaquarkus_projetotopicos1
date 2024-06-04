package br.unitins.topicos1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento extends DefaultEntity {

    @OneToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pagamento", nullable = false)
    private TipoPagamento tipoPagamento;

    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Boleto boleto;

    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pix pix;

    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private CartaoCredito cartaoCredito;

    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private CartaoDebito cartaoDebito;

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public Pix getPix() {
        return pix;
    }

    public void setPix(Pix pix) {
        this.pix = pix;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public CartaoDebito getCartaoDebito() {
        return cartaoDebito;
    }

    public void setCartaoDebito(CartaoDebito cartaoDebito) {
        this.cartaoDebito = cartaoDebito;
    }

}