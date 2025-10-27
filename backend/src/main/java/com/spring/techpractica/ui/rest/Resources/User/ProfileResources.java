package com.spring.techpractica.ui.rest.Resources.User;

import com.spring.techpractica.ui.rest.Resources.Session.SessionCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResources {
    private final UserResources user;
    private final SessionCollection sessions;
}
