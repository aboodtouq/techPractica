package com.spring.techpractica.service;

import com.spring.techpractica.model.entity.Field;
import com.spring.techpractica.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }


    public List<Field> findAllFields() {
        return fieldRepository.findAll();
    }
}
