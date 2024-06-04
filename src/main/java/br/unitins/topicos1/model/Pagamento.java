package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento extends DefaultEntity {

    @OneToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @OneToOne(mappedBy = "pagamento")
    private Boleto boleto;

    @OneToOne(mappedBy = "pagamento")
    private Pix pix;

    @OneToOne(mappedBy = "pagamento")
    private CartaoCredito cartaoCredito;

    @OneToOne(mappedBy = "pagamento")
    private CartaoDebito cartaoDebito;

    // Getters and setters for boleto, pix, cartaoCredito, and cartaoDebito
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
