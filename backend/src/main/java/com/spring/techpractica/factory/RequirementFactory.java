package com.spring.techpractica.factory;

import com.spring.techpractica.model.entity.Requirement;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.techSkills.Field;
import org.springframework.stereotype.Component;

@Component
public class RequirementFactory {

    private RequirementFactory() {

    }

    public static Requirement createRequirement(Session session, Field field) {

        return Requirement.builder()
                .session(session)
                .field(field)
                .build();
    }
}
