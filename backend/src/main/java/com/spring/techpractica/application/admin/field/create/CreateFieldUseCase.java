package com.spring.techpractica.application.admin.field.create;

import com.spring.techpractica.core.Field.Entity.Field;
import com.spring.techpractica.core.Field.FieldFactory;
import com.spring.techpractica.core.Field.FieldRepository;
import com.spring.techpractica.core.Shared.Exception.ResourcesDuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateFieldUseCase {
    private final FieldRepository fieldRepository;
    private final FieldFactory fieldFactory;

    @Transactional
    public Field execute(CreateFieldCommand command) {
        String name = command.name();
        if (fieldRepository.existsByName(name)) {
            throw new ResourcesDuplicateException(name);
        }
        Field field = fieldFactory.create(name);
        return fieldRepository.save(field);
    }
}
