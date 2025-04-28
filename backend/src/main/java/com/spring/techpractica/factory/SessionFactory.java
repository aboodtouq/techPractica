package com.spring.techpractica.factory;

import com.spring.techpractica.dto.session.SessionRequest;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.model.entity.Session;
import org.springframework.stereotype.Component;

@Component

public class SessionFactory {


    public Session createFrom(SessionRequest request) {
        return SessionMapper.sessionCreatorToSession(request);
    }

}
