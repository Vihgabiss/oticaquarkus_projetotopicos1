package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Telefone extends DefaultEntity{
    
    @Column(length = 2)
    private String codigoArea;

    @Column(length = 9)
    private String numero;

    public Telefone(String codigoArea, String numero) {
        
    }

    public Telefone() {

    }

    public String getCodigoArea() {
        return codigoArea;
    }
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
}
