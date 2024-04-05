package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.TipoArmacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoArmacaoConverter implements AttributeConverter<TipoArmacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoArmacao tipoArmacao) {
        return (tipoArmacao == null ? null : tipoArmacao.getId());
    }

    @Override
    public TipoArmacao convertToEntityAttribute(Integer id) {
        return TipoArmacao.valueOf(id);
    }
}