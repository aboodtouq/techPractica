package com.spring.techpractica.mengmentData;


import com.spring.techpractica.dto.techSkills.FieldTransfer;
import com.spring.techpractica.maper.FieldMapper;
import com.spring.techpractica.model.entity.techSkills.Field;
import com.spring.techpractica.repository.techSkills.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldManagementData {

    private final FieldRepository fieldRepository;

    private final FieldMapper fieldMapper;

    public FieldManagementData(FieldRepository fieldRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
    }

    public List<FieldTransfer> getAllFields() {
        return fieldMapper.fieldToFieldTransferList(fieldRepository.findAll());
    }

    public Optional<Field> findFieldByFieldName(String fieldName) {
        return fieldRepository.findById(fieldName);
    }
}
