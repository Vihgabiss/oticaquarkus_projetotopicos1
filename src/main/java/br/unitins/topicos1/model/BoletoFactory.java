package br.unitins.topicos1.model;

import java.time.LocalDate;

import org.apache.commons.lang3.RandomStringUtils;

public class BoletoFactory {
    public static Boleto criarBoleto() { // Sem parâmetro codigoBarras
        Boleto boleto = new Boleto();
        
        // Código de barras aleatório de 44 dígitos
        boleto.setCodigoBarras(RandomStringUtils.randomNumeric(44));
        
        boleto.setDataVencimento(LocalDate.now().plusDays(3)); 
        return boleto;
    }
}
