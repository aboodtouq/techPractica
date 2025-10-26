package com.spring.techpractica.application.admin.field.get.all;

import com.spring.techpractica.core.Field.Entity.Field;
import com.spring.techpractica.core.Field.FieldRepository;
import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllFieldsUseCase {
    private final FieldRepository fieldRepository;

    public List<Field> execute(GetAllFieldsCommand command) {
        List<Field> fields = fieldRepository.findAll();

        if (fields.isEmpty()) {
            throw new ResourcesNotFoundException("No fields found");
        }

        return fields;
    }
}
