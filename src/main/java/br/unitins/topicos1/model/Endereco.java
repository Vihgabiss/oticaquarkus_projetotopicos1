package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Endereco extends DefaultEntity {

    @Column(length = 9)
    private String cep;

    @Column(length = 60)
    private String bairro;

    @Column(length = 60)
    private String rua;

    @Column(length = 20)
    private Integer numero;

    @Column(length = 80)
    private String complemento;

    @Column(length = 20)
    private String cidade;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    // public Cidade getCidade() {
    //     return cidade;
    // }

    // public void setCidade(Cidade cidade) {
    //     this.cidade = cidade;
    // }

    // @ManyToOne
    // @JoinColumn(name = "id_cidade")
    // private Cidade cidade;

}
