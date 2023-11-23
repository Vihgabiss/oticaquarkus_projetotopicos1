package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cidade extends DefaultEntity{
    
    @Column(length = 60, nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
