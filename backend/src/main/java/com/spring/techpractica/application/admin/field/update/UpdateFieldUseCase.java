package com.spring.techpractica.application.admin.field.update;

import com.spring.techpractica.core.Field.Entity.Field;
import com.spring.techpractica.core.Field.FieldRepository;
import com.spring.techpractica.core.Shared.Exception.ResourcesDuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateFieldUseCase {
    private final FieldRepository fieldRepository;

    @Transactional
    public Field execute(UpdateFieldCommand command) {
        String name = command.name();
        Field field =fieldRepository.getOrThrowByID(command.fieldId());

        if (fieldRepository.existsByName(name)) {
            throw new ResourcesDuplicateException("Field with name : " + name + " already exists");
        }
        field.setName(name);
        return fieldRepository.save(field);
    }
}
