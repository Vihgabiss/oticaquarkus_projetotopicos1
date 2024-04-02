package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.MaterialArmacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MaterialArmacaoConverter implements AttributeConverter<MaterialArmacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MaterialArmacao materialArmacao) {
        return (materialArmacao == null ? null : materialArmacao.getId());
    }

    @Override
    public MaterialArmacao convertToEntityAttribute(Integer id) {
        return MaterialArmacao.valueOf(id);
    }
}
