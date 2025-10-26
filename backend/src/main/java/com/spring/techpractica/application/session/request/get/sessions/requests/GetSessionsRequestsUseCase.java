package com.spring.techpractica.application.session.request.get.sessions.requests;

import com.spring.techpractica.core.Request.Entity.Request;
import com.spring.techpractica.core.Session.Entity.Session;
import com.spring.techpractica.core.Session.SessionRepository;
import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.User.Exception.UserAuthenticationException;
import com.spring.techpractica.core.User.User;
import com.spring.techpractica.core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetSessionsRequestsUseCase {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;


    public List<Request> execute(GetSessionsRequestsCommand command) {

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.userId()));

        Session session = sessionRepository.findById(command.sessionId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.sessionId()));

        if(!session.isOwner(user.getId())){
            throw new UserAuthenticationException("You are not owner of this session");
        }

        List<Request> requests = sessionRepository.getRequestsBySession(command.sessionId());

        if(requests.isEmpty()){
            requests = new ArrayList<>();
        }

        return requests;
    }
}
