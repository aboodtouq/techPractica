package com.spring.techpractica.application.session.GetSessions.GetSessionBySpecifications;

public record GetSessionsBySpecificationsCommand(String sort,
                                                 String fieldName,
                                                 String sessionName,
                                                 int page,
                                                 int size) {
}
