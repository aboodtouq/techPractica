package com.spring.techpractica.UI.Rest.Controller.Session.CreateSession;


    public record CreateSessionRequest(String name,
                                   String description, boolean isPrivate,
                                   String system, String field, String technology){
}