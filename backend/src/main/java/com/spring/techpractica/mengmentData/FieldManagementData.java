package com.spring.techpractica.mengmentData;


import com.spring.techpractica.dto.techSkills.FieldTransfer;
import com.spring.techpractica.maper.FieldMapper;

import com.spring.techpractica.model.entity.techSkills.Category;

import com.spring.techpractica.model.entity.techSkills.Field;
import com.spring.techpractica.repository.techSkills.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldManagementData {

    private final FieldRepository fieldRepository;

    public FieldManagementData(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Optional<Field> findFieldByFieldName(String fieldName) {
        return fieldRepository.findById(fieldName);
    }

}
