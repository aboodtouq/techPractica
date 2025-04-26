package com.spring.techpractica.maper;


import com.spring.techpractica.dto.techSkills.FieldTransfer;
import com.spring.techpractica.model.entity.techSkills.Field;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FieldMapper {

    public FieldTransfer fieldToFieldTransfer(Field field) {

        return FieldTransfer.builder().fieldName(field.getFieldName())
                .build();
    }

    public List<FieldTransfer> fieldToFieldTransferList(List<Field> fields) {
        return fields.stream().map(this::fieldToFieldTransfer).toList();
    }
}
