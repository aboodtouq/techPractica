package com.spring.techpractica.core.Requirement;

import com.spring.techpractica.core.Field.Entity.Field;
import com.spring.techpractica.core.Requirement.Entity.Requirement;
import com.spring.techpractica.core.Session.Entity.Session;
import org.springframework.stereotype.Component;

@Component
public class RequirementFactory {

    public Requirement create(Session session, Field field) {
        return Requirement.builder()
                .session(session)
                .field(field)
                .build();
    }
}
