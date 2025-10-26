package com.spring.techpractica.core.Field;

import com.spring.techpractica.core.Field.Entity.Field;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FieldFactory {

    public Field create(String name) {


        return Field.builder()
                .name(name)
                .build();
    }
}
