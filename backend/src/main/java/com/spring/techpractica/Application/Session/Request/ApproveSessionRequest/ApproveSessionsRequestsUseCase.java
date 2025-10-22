package com.spring.techpractica.Application.Session.Request.ApproveSessionRequest;

import com.spring.techpractica.Core.Request.Entity.Request;
import com.spring.techpractica.Core.Request.RequestRepository;
import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.Core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.Core.User.Exception.UserAuthenticationException;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApproveSessionsRequestsUseCase {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final RequestRepository requestRepository;

    public Request execute(ApproveSessionsRequestsCommand command) {

        User user = userRepository.findById(command.ownerId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.ownerId()));

        Session session = sessionRepository.findById(command.sessionId())
                .orElseThrow(() -> new ResourcesNotFoundException(command.sessionId()));

        if(!session.isOwner(user.getId())){
            throw new UserAuthenticationException("You are not owner of this session");
        }

        List<Request> requests = sessionRepository.getRequestsBySession(command.sessionId());


        Request request = requests.stream()
                .filter(r -> r.getId().equals(command.requestId()))
                .findFirst()
                .orElseThrow(() -> new ResourcesNotFoundException(command.requestId()));

        request.approve();

        return requestRepository.save(request);
    }
}
