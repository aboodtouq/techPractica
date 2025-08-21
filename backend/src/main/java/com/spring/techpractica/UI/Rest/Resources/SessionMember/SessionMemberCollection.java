package com.spring.techpractica.UI.Rest.Resources.SessionMember;

import lombok.Getter;

import java.util.List;

@Getter
public class SessionMemberCollection {
    private final List<SessionMemberResources> members;


    public SessionMemberCollection(List<SessionMemberResources> members) {
        this.members = members.stream().map(
                member -> new SessionMemberResources(
                        member.getId(),
                        member.getUser(),
                        member.getRole()
                )
        ).toList();
    }
}
