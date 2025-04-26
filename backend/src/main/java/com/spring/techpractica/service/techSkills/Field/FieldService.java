package com.spring.techpractica.service.techSkills.Field;

import com.spring.techpractica.dto.techSkills.FieldTransfer;
import com.spring.techpractica.mengmentData.FieldManagementData;
import com.spring.techpractica.model.entity.techSkills.Field;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//I hate Abood

@Service
public class FieldService {


    private final FieldManagementData fieldManagementData;

    public FieldService(FieldManagementData fieldManagementData) {


        this.fieldManagementData = fieldManagementData;


    }

    public List<Field> getAllFields() {
        return fieldManagementData.getAllFields();
    }

}
