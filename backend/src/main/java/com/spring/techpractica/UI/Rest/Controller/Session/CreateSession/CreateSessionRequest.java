package com.spring.techpractica.UI.Rest.Controller.Session.CreateSession;


import com.spring.techpractica.UI.Rest.Controller.Session.CreateSession.Dto.RequirementRequest;

import java.util.List;

public record CreateSessionRequest(String name,
                                   String description, boolean isPrivate,
                                   String system, List<RequirementRequest> requirements){
}