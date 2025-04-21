package com.spring.techpractica.maper;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.model.entity.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessionMapper {


    public Session sessionCreatorToSession(SessionCreatorRequest sessionCreatorRequest) {

        return Session.builder()
                .sessionName(sessionCreatorRequest.getNameSession())
                .sessionDescription(sessionCreatorRequest.getDescriptionSession())
                .isPrivate(sessionCreatorRequest.isPrivateSession())
                .sessionCategories(new ArrayList<>())
                .sessionRequests(new ArrayList<>())
                .sessionRequirements(new ArrayList<>())
                .sessionMembers(new ArrayList<>())
                .timestampList(new ArrayList<>())
                .sessionTechnologies(new ArrayList<>())
                .build();
    }

    public SessionResponse sessionToSessionResponse(Session session) {
        return SessionResponse.
                builder()
                .sessionName(session.getSessionName())
                .sessionDescription(session.getSessionDescription())
                .category(session.getSessionCategories().get(0))
                .technologies(session.getSessionTechnologies())
                .build();
    }

}
