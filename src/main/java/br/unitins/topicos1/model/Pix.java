package br.unitins.topicos1.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Pix extends DefaultEntity {
    private String chavePix;
    private String qrCode;

    @OneToOne
    @JoinColumn(name = "id_pagamento") // Chave estrangeira para Pagamento
    private Pagamento pagamento;

    public Pix() { // Construtor para gerar dados aleatórios
        this.chavePix = gerarChavePixAleatoria();
        this.qrCode = gerarQRCodePix(this.chavePix); // (opcional)
    }
     private String gerarChavePixAleatoria() {
        int tipoChave = new Random().nextInt(5); // 0 a 4
        switch (tipoChave) {
            case 0:
                return RandomStringUtils.randomNumeric(11); // CPF
            case 1:
                return RandomStringUtils.randomNumeric(14); // CNPJ
            case 2:
                return RandomStringUtils.randomAlphanumeric(10) + "@email.com"; // Email
            case 3:
                return RandomStringUtils.randomNumeric(11); // Telefone
            case 4:
                return RandomStringUtils.randomAlphanumeric(20, 35); // Chave aleatória
            default:
                return null; // Não deveria acontecer
        }
    }

    private String gerarQRCodePix(String chavePix) { // (opcional)
        if (chavePix == null) {
            return null;
        }

        try {
            // Conteúdo do QR Code (simplificado, você precisará ajustar)
            String conteudoQRCode = "00020126330014br.gov.bcb.pix01" + chavePix;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(conteudoQRCode, BarcodeFormat.QR_CODE, 200, 200);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(pngData);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
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