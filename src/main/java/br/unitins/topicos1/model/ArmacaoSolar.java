package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ArmacaoSolar extends Armacao {
    private Long id;

    private TipoLenteSolar tipoLenteSolar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLenteSolar getTipoLenteSolar() {
        return tipoLenteSolar;
    }

    public void setTipoLenteSolar(TipoLenteSolar tipoLenteSolar) {
        this.tipoLenteSolar = tipoLenteSolar;
    }

}
