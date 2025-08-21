package com.spring.techpractica.Application.Session.CreateSession;

import com.spring.techpractica.Core.Request.Model.RequirementRequest;

import java.util.UUID;

public record CreateSessionCommand (UUID userId, String name,
                                    String description, boolean isPrivate,
                                    String system, RequirementRequest requirement) {
}