package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.TipoOculos;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoOculosConverter implements AttributeConverter<TipoOculos, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoOculos tipoOculos) {
        return (tipoOculos == null ? null : tipoOculos.getId());
    }

    @Override
    public TipoOculos convertToEntityAttribute(Integer id) {
        return TipoOculos.valueOf(id);
    }
}
