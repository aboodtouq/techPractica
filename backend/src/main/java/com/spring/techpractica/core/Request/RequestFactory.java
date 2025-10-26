package com.spring.techpractica.core.Request;

import com.spring.techpractica.core.Request.Entity.Request;
import com.spring.techpractica.core.Request.model.RequestState;
import com.spring.techpractica.core.Requirement.Entity.Requirement;
import com.spring.techpractica.core.User.User;
import org.springframework.stereotype.Component;

@Component
public class RequestFactory {

    public Request createRequest(User user, Requirement requirement, String brief) {
        return Request.builder()
                .user(user)
                .requirement(requirement)
                .brief(brief)
                .requestStatus(RequestState.PENDING)
                .build();
    }
}
