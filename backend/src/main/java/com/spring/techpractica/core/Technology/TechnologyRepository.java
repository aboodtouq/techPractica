package com.spring.techpractica.core.Technology;

import com.spring.techpractica.core.Shared.BaseRepository;
import com.spring.techpractica.core.Technology.Entity.Technology;

import java.util.*;

public interface TechnologyRepository extends BaseRepository<Technology> {
    boolean existsByName(String name);

    List<Technology> findAllByNames(Collection<String> names);

    Optional<Technology> findTechnologyByName(String technology);

    List<Technology> findAll();

    List<Technology> findAllByIds(Set<UUID> ids);

    List<Technology> findAllByFieldId(UUID fieldId);

    void deleteById(UUID id);
}

