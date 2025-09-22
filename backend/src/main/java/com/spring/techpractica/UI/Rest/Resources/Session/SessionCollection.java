package com.spring.techpractica.UI.Rest.Resources.Session;

import com.fasterxml.jackson.annotation.JsonValue;
import com.spring.techpractica.Core.Session.Entity.Session;
import lombok.Getter;

import java.util.List;

@Getter
public class SessionCollection {

    private final List<SessionResources> sessions;

    private final int totalItems;

    private final int totalPages;

    private final int pageSize=6;

    public SessionCollection(List<Session> sessions) {
        this.sessions = sessions.stream().map(SessionResources::new)
                .toList();
        this.totalItems = sessions.size();

        this.totalPages = (int) Math.ceil((double) sessions.size() /pageSize);
    }

}