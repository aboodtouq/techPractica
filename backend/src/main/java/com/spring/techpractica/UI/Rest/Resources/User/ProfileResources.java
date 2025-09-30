package com.spring.techpractica.UI.Rest.Resources.User;

import com.spring.techpractica.UI.Rest.Resources.Session.SessionCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResources {
    private final UserResources user;
    private final SessionCollection sessions;
}
