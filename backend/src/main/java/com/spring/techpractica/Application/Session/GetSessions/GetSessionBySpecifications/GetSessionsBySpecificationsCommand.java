package com.spring.techpractica.Application.Session.GetSessions.GetSessionBySpecifications;

public record GetSessionsBySpecificationsCommand(String sort,
                                                 String fieldName,
                                                 String sessionName,
                                                 int page,
                                                 int size) {
}
