package com.spring.techpractica.service.session;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.repository.SessionRepository;
import com.spring.techpractica.service.session.createSession.CreateSessionService;
import com.spring.techpractica.service.techSkills.CategoryService;
import com.spring.techpractica.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapper;

    private final UserService userService;

    private final CategoryService categoryService;

    private final CreateSessionService createSessionService;

    public SessionResponse createSession(SessionCreatorRequest sessionCreatorRequest,
                                         String userEmail) {

        return createSessionService.createSession(sessionCreatorRequest, userEmail);
    }

    public List<SessionResponse> getSessionsByUserEmail(String userEmail, int pageSize, int pageNumber) {

        User user = userService.findUserByUserEmail(userEmail);

        if (user.getUserTechnologies() == null || user.getUserTechnologies().isEmpty()) {

            if (pageNumber < 0 || pageSize <= 0) {
                throw new ResourcesNotFoundException("Page number or Size is negative");
            }

            Pageable sessionPage = PageRequest.of(pageNumber, pageSize);
            Page<Session> page = sessionRepository.findAll(sessionPage);
            return page.map(sessionMapper::sessionToSessionResponse).stream().toList();
        }
        return null;
    }

    public List<SessionResponse> getSessionsByCategoryName(String categoryName, int pageSize, int pageNumber) {

        Category category = categoryService.findCategoryByName(categoryName);


        if (pageNumber < 0 || pageSize <= 0) {
            throw new ResourcesNotFoundException("Page number or Size is negative");
        }

        Pageable sessionPage = PageRequest.of(pageNumber, pageSize);
        Page<Session> page = sessionRepository.findAllBySessionCategories(category, sessionPage);


        return page.map(sessionMapper::sessionToSessionResponse).stream().toList();
    }
}
