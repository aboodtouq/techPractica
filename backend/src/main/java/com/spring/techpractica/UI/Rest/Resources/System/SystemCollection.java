package com.spring.techpractica.UI.Rest.Resources.System;

import com.spring.techpractica.core.system.entity.System;
import lombok.Getter;

import java.util.List;

@Getter
public class SystemCollection {
    private final List<SystemResources> systems;

    public SystemCollection(List<System> systems) {
        this.systems = systems.stream().map(SystemResources::new).toList();
    }
}
