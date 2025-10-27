package com.spring.techpractica.ui.rest.Resources.Session;

import com.spring.techpractica.core.session.entity.Session;
import com.spring.techpractica.core.session.SessionStatus;
import com.spring.techpractica.ui.rest.Resources.Requirment.RequirementCollection;
import com.spring.techpractica.ui.rest.Resources.System.SystemResources;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class SessionResources {

    private UUID id;
    private String name;
    private String description;
    private boolean isPrivate;
    private SessionStatus status;
    private SystemResources system;
    private RequirementCollection requirements;
    private String ownerFullName;


    public SessionResources(Session session) {
        this.id = session.getId();
        this.name = session.getName();
        this.description = session.getDescription();
        this.isPrivate = session.isPrivate();
        this.status = session.getStatus();
        this.ownerFullName = session.getOwnerFullName();

        if (session.getSystems() != null && !session.getSystems().isEmpty()) {
            this.system = new SystemResources(session.getSystems().getLast());
        }

        if (session.getRequirements() != null && !session.getRequirements().isEmpty()) {
            this.requirements = new RequirementCollection(session.getRequirements());
        }
    }
}