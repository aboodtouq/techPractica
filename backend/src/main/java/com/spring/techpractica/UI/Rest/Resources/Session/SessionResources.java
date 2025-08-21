package com.spring.techpractica.UI.Rest.Resources.Session;

import com.spring.techpractica.UI.Rest.Resources.Requirment.RequirementCollection;
import com.spring.techpractica.UI.Rest.Resources.Requirment.RequirementResources;
import com.spring.techpractica.UI.Rest.Resources.SessionMember.SessionMemberCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class SessionResources {

    private UUID id;
    private String name;
    private String description;
    private boolean isPrivate;
    private boolean isRunning;
    private String system;
    private RequirementCollection requirementCollection;
    private SessionMemberCollection members;
}