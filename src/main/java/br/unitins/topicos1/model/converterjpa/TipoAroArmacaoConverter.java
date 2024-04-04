package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.TipoAroArmacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAroArmacaoConverter implements AttributeConverter<TipoAroArmacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoAroArmacao tipoAroArmacao) {
        return (tipoAroArmacao == null ? null : tipoAroArmacao.getId());
    }

    @Override
    public TipoAroArmacao convertToEntityAttribute(Integer id) {
        return TipoAroArmacao.valueOf(id);
    }
}