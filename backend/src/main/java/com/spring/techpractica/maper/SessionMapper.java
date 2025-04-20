package com.spring.techpractica.maper;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.model.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {


    public Session sessionCreatorToSession(SessionCreatorRequest sessionCreatorRequest) {

        return Session.builder()
                .sessionName(sessionCreatorRequest.getNameSession())
                .sessionDescription(sessionCreatorRequest.getDescriptionSession())
                .isPrivate(sessionCreatorRequest.isPrivateSession())
                .sessionRequirements(sessionCreatorRequest.getRequirements())
                .build();
    }

}
