package com.spring.techpractica.service.techSkills;

import com.spring.techpractica.dto.techSkills.FieldTransfer;
import com.spring.techpractica.maper.TechSkillMapper;
import com.spring.techpractica.model.entity.techSkills.Field;
import com.spring.techpractica.repository.techSkills.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;
    private final TechSkillMapper techSkillMapper;

    public FieldService(FieldRepository fieldRepository, TechSkillMapper techSkillMapper) {

        this.fieldRepository = fieldRepository;
        this.techSkillMapper = techSkillMapper;
    }


    public List<FieldTransfer> findAllFields() {

        return techSkillMapper.fieldToFieldTransferList(fieldRepository.findAll());
    }

    public Optional<Field> findFieldByFieldName(String fieldName) {
        return fieldRepository.findById(fieldName);
    }
}
