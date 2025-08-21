package com.spring.techpractica.UI.Rest.Controller.Session.CreateSession;


    public record CreateSessionRequest(String title,
                                   String description, boolean isPublic,
                                   String system, String field, String technology){
}