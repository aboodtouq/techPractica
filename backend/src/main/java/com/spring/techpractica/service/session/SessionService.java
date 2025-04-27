package com.spring.techpractica.service.session;

import com.spring.techpractica.dto.session.SessionRequest;
import com.spring.techpractica.dto.session.SessionResponse;
import com.spring.techpractica.factory.PageRequestFactory;
import com.spring.techpractica.maper.SessionMapper;
import com.spring.techpractica.mengmentData.*;
import com.spring.techpractica.model.SessionRole;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.model.entity.techSkills.Category;
import com.spring.techpractica.service.session.createSession.CreateSessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionManagementData sessionManagementData;

    private final UserManagementData userManagementData;

    private final CategoryManagementData categoryManagementData;

    private final CreateSessionService createSessionService;

    private final TechnologyManagementData technologyManagementData;

    private final FieldManagementData fieldManagementData;

    private final AuthenticatedUserSessionManagementData authenticatedUserSessionManagementData;

    public SessionResponse createSession(SessionRequest sessionRequest,
                                         String userEmail) {

        return createSessionService.createSession(sessionCreatorRequest,
                userEmail);
    }


    public List<SessionResponse> getSessionsByUserEmail(String userEmail,
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
                .getSessionsByCategoryAndPageable(category, PageRequestFactory.createPageRequest(pageSize, pageNumber));

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

    @Transactional
    public SessionResponse updateSession(Long sessionId,
                                         SessionRequest updatedSessionRequest,
                                         String userEmail) {

        User user = userManagementData.getUserByEmail(userEmail);

        Session session = sessionManagementData.getSessionById(sessionId);

        if (getSessionRole(user.getUserId(),sessionId) != SessionRole.OWNER){
                throw new AuthenticationException("User must be an OWNER to perform this action.");
        }


        session.setSessionTechnologies(technologyManagementData
                .getTechnologiesByTechnologiesName(updatedSessionRequest.getTechnologies()));

        session.setSessionCategories
                (categoriesStringToCategoriesList
                        (List.of(updatedSessionRequest.getCategory())));

        List <Requirement> requirements = updatedSessionRequest
                .getFields().stream().map((field)->
                        RequirementFactory
                                .createRequirement(session
                                        ,fieldManagementData.getFieldByFieldName(field)))
                .toList();

        session.setSessionRequirements(requirements);

        session.setSessionFields(fieldManagementData
                .getFieldsByFieldsName(updatedSessionRequest.getFields()));

        sessionManagementData.saveSession(session);

        return SessionMapper.sessionToSessionResponse(session);
    }

    private List<Category> categoriesStringToCategoriesList(List<String> categories) {
        return categories.stream()
                .map(categoryManagementData::getCategoryByName)
                .toList();
    }

    private List<Field> fieldsStringToFieldsList(List<String> fields) {
        return fields.stream()
                .map(fieldManagementData::getFieldByFieldName)
                .toList();
    }

    public boolean isUserOwnerOfSession(User user, Session session) {
        return user.getAuthenticatedUserSessions().stream()
                .anyMatch(aus -> aus.getSession().getSessionId() == session.getSessionId()
                        && aus.getScopedRole() == SessionRole.OWNER);
    }

    public SessionRole getSessionRole(Long userId, Long sessionId) {

        AuthenticatedUserSession authenticatedUserSession = authenticatedUserSessionManagementData
                .findByUserUserIdAndUserSessionId(userId, sessionId)
                .orElseThrow(() -> new AuthenticationException("User is not authenticated"));

        return authenticatedUserSession.getScopedRole();

    }

}