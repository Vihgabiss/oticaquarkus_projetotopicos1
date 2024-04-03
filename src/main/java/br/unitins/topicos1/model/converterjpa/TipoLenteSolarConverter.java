package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.TipoLenteSolar;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoLenteSolarConverter implements AttributeConverter<TipoLenteSolar, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoLenteSolar tipoLenteSolar) {
        return (tipoLenteSolar == null ? null : tipoLenteSolar.getId());
    }

    @Override
    public TipoLenteSolar convertToEntityAttribute(Integer id) {
        return TipoLenteSolar.valueOf(id);
    }
}
