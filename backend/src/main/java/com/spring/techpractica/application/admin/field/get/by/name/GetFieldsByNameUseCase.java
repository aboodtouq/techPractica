package com.spring.techpractica.application.admin.field.get.by.name;

import com.spring.techpractica.Core.Field.Entity.Field;
import com.spring.techpractica.Core.Field.FieldRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetFieldsByNameUseCase {
    private final FieldRepository fieldRepository;

    public List<Field> execute(GetFieldsByNameCommand command) {
        List<Field> fields = fieldRepository.findAllByNames(command.fieldsNames());

        if (fields.size() != command.fieldsNames().size()) {
            throw new ResourcesNotFoundException(command.fieldsNames());
        }
        return fields;
    }
}
