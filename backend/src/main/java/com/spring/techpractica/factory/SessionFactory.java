package com.spring.techpractica.factory;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.model.entity.Session;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SessionFactory {

    private final SessionMapper sessionMapper;

    public Session createFrom(SessionCreatorRequest request) {
        return sessionMapper.sessionCreatorToSession(request);
    }

}
