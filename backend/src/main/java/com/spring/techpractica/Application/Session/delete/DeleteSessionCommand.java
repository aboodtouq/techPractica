package com.spring.techpractica.Application.Session.delete;

import com.spring.techpractica.Core.Requirement.Model.RequirementRequest;

import java.util.List;
import java.util.UUID;

public record DeleteSessionCommand(UUID userId, UUID sessionId) {
}