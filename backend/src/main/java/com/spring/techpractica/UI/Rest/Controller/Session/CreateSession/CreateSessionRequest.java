package com.spring.techpractica.UI.Rest.Controller.Session.CreateSession;


import java.util.List;

public record CreateSessionRequest(String name,
                                   String description, boolean isPrivate,
                                   String system, String field, List<String> technologies){
}