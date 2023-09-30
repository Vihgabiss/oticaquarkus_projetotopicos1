package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Marca extends DefaultEntity{

    @Column(length = 60, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_oculos")
    private Oculos oculos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Oculos getOculos() {
        return oculos;
    }

    public void setOculos(Oculos oculos) {
        this.oculos = oculos;
    }
}
