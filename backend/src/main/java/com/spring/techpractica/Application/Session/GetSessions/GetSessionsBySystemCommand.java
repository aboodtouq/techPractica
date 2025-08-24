package com.spring.techpractica.Application.Session.GetSessions;

public record GetSessionsBySystemCommand(String systemName,
                                         int sizeOfPage,
                                         int pageNumber) {
}
