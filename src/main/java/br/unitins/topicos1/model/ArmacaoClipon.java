package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class ArmacaoClipon extends Armacao {
    @Column(name = "tipo_lente_solar")
    private TipoLenteSolar tipoLenteSolar;

    public TipoLenteSolar getTipoLenteSolar() {
        return tipoLenteSolar;
    }

    public void setTipoLenteSolar(TipoLenteSolar tipoLenteSolar) {
        this.tipoLenteSolar = tipoLenteSolar;
    }

}
