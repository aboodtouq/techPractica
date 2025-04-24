package com.spring.techpractica.service.session;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.factory.PageRequestFactory;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.service.session.createSession.CreateSessionService;
import com.spring.techpractica.service.techSkills.CategoryService;
import com.spring.techpractica.service.user.UserManagementData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionManagementData sessionManagementData;

    private final UserManagementData userManagementData;

    private final CategoryService categoryService;

    private final CreateSessionService createSessionService;


    public SessionResponse createSession(SessionCreatorRequest sessionCreatorRequest,
                                         String userEmail) {

        return createSessionService.createSession(sessionCreatorRequest, userEmail);
    }


    public List<SessionResponse> getSessionsByUserEmail(String userEmail,
                                                        int pageSize,
                                                        int pageNumber) {

        User user = userManagementData.getUserByEmail(userEmail);

        if (user.getUserTechnologies() == null || user.getUserTechnologies().isEmpty()) {

            List<Session> sessions = sessionManagementData.getSessionsByPageable(
                    PageRequestFactory.createPageRequest(pageNumber, pageSize));

            return SessionMapper.sessionsToSessionResponses(sessions);
        }
        return null;
    }

    public List<SessionResponse> getSessionsByCategoryName(String categoryName, int pageSize, int pageNumber) {

        Category category = categoryService
                .findCategoryByName(categoryName);

        List<Session> sessions = sessionManagementData
                .getSessionsByPageable(PageRequestFactory.createPageRequest(pageSize, pageNumber));

        return SessionMapper.sessionsToSessionResponses(sessions);

    }


}
