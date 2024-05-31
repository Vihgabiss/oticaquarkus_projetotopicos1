package br.unitins.topicos1.model;

import java.time.LocalDate;

public class BoletoFactory {
    public static Boleto criarBoleto(String codigoBarras) {
        Boleto boleto = new Boleto();
        boleto.setCodigoBarras(codigoBarras);
        boleto.setDataVencimento(LocalDate.now().plusDays(30)); // Calcula a data de vencimento
        return boleto;
    }
}
