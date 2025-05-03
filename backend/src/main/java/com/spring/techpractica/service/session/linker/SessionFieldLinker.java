package com.spring.techpractica.service.session.linker;

import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.mengmentData.FieldManagementData;
import com.spring.techpractica.model.entity.Requirement;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.techSkills.Field;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SessionFieldLinker {

    private final FieldManagementData fieldManagementData;

    public void linkFieldsToSession(Session session, List<String> fieldNames) {
        fieldNames.forEach(name -> {
            Field field = fieldManagementData.findFieldByFieldName(name)
                    .orElseThrow(() -> new ResourcesNotFoundException("Field not found"));
            session.getSessionFields().add(field);
            Requirement requirement = Requirement.builder()
                    .field(field)
                    .session(session)
                    .build();
            session.getSessionRequirements().add(requirement);
        });
    }

}
