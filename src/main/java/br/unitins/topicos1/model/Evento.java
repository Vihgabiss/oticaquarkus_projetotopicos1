package br.unitins.topicos1.model;

import jakarta.persistence.Entity;

@Entity
public class Evento extends DefaultEntity {
    private String descricao;
    private String nome;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
