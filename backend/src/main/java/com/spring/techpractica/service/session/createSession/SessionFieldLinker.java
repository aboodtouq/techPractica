package com.spring.techpractica.service.session.createSession;

import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.Requirement;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.techSkills.Field;
import com.spring.techpractica.service.techSkills.Field.FieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SessionFieldLinker {

    private final FieldService fieldService;

    public void linkFieldsToSession(Session session, List<String> fieldNames) {
        fieldNames.forEach(name -> {
            Field field = fieldService.findFieldByFieldName(name)
                    .orElseThrow(() -> new ResourcesNotFoundException("Field not found"));
            Requirement requirement = Requirement.builder()
                    .field(field)
                    .session(session)
                    .build();
            session.getSessionRequirements().add(requirement);
        });
    }

}
