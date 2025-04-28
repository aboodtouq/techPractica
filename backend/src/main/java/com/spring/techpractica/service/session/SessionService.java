package com.spring.techpractica.service.session;

import com.spring.techpractica.dto.session.SessionCreatorRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.dto.session.SessionsResponse;
import com.spring.techpractica.factory.PageRequestFactory;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.mengmentData.CategoryManagementData;
import com.spring.techpractica.mengmentData.SessionManagementData;
import com.spring.techpractica.mengmentData.UserManagementData;

import com.spring.techpractica.mengmentData.AuthenticatedUserSessionManagementData;

import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.service.session.createSession.CreateSessionService;

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionManagementData sessionManagementData;

    private final UserManagementData userManagementData;

    private final CategoryManagementData categoryManagementData;

    private final CreateSessionService createSessionService;

    private final AuthenticatedUserSessionManagementData authenticatedUserSessionManagementData;

/*
10 controller
 */
    public SessionResponse createSession(SessionCreatorRequest sessionCreatorRequest,
                                         String userEmail) {

        return createSessionService.createSession(sessionCreatorRequest, userEmail);
    }


    public SessionsResponse getSessionsByUserEmail(String userEmail,
                                                   int pageSize,
                                                   int pageNumber) {

        User user = userManagementData.getUserByEmail(userEmail);

        if (user.getUserTechnologies() == null || user.getUserTechnologies().isEmpty()) {

            List<Session> sessions = sessionManagementData.getSessionsByPageable(
                    PageRequestFactory.createPageRequest(pageSize, pageNumber));

            long totalSession= sessionManagementData.getNumberOfSessions();

            return SessionMapper.sessionsAndTotalSessionsToSessionsResponses(sessions,totalSession);
        }
        return null;
    }

    public SessionsResponse getSessionsByCategoryName(String categoryName, int pageSize, int pageNumber) {

        Category category = categoryManagementData.getCategoryByName(categoryName);

        List<Session> sessions = sessionManagementData
                .getSessionsByCategoryAndPageable(category,PageRequestFactory.createPageRequest(pageSize, pageNumber));

        long totalSession= sessionManagementData.getNumberOfCategorySessions(category);

        return SessionMapper.sessionsAndTotalSessionsToSessionsResponses(sessions,totalSession);

    }


    public SessionsResponse getUserSessions(String userEmail, int pageSize, int pageNumber) {
        User user = userManagementData.getUserByEmail(userEmail);

      List<Session> sessions=  authenticatedUserSessionManagementData.getUserSessionsByPageable
               (user,PageRequestFactory.createPageRequest(pageSize, pageNumber));

      long totalSession= authenticatedUserSessionManagementData.getNumberOfUserSessions(user);


        return SessionMapper.sessionsAndTotalSessionsToSessionsResponses(sessions,totalSession);
    }
  
    @Transactional
    public void deleteSessionByUserEmailAndSessionId(String username
            , Long sessionId) {

        //Validation ---
        Session session = sessionManagementData.getSessionById(sessionId);
        sessionManagementData.deleteSession(session);

    }

}