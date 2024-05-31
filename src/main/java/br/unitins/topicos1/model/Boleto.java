package br.unitins.topicos1.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Boleto extends DefaultEntity {
    private String codigoBarras;
    private LocalDate dataVencimento;
    private String qrCode;

    @OneToOne
    @JoinColumn(name = "id_pagamento") // Chave estrangeira para Pagamento
    private Pagamento pagamento;

    public Boleto(String codigoBarras) {
        this.codigoBarras = codigoBarras;
        this.dataVencimento = LocalDate.now().plusDays(30); // Calcula a data de vencimento
    }

    public Boleto() {
        // Construtor vazio necessário para o Hibernate
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

}