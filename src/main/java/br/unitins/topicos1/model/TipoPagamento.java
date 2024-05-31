package br.unitins.topicos1.model;

import jakarta.persistence.Entity;

@Entity
public class TipoPagamento extends DefaultEntity {

    private String nome; // Ex: "Boleto", "Pix", "Cartão de Crédito"

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
