package com.spring.techpractica.infrastructure.Jpa.Technology;

import com.spring.techpractica.core.Shared.Exception.ResourcesNotFoundException;
import com.spring.techpractica.core.Technology.Entity.Technology;
import com.spring.techpractica.core.Technology.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@AllArgsConstructor
public class JpaTechnologyRepository implements TechnologyRepository {
    private final JpaTechnology jpaTechnology;

    @Override
    public Technology save(Technology technology) {
        return jpaTechnology.save(technology);
    }

    @Override
    public Technology update(Technology technology) {
        UUID id = technology.getId();
        if (jpaTechnology.existsById(id)) {
            return jpaTechnology.save(technology);
        }
        throw new ResourcesNotFoundException(id);
    }

    @Override
    public Optional<Technology> findById(UUID id) {
        return jpaTechnology.findById(id);
    }

    @Override
    public List<Technology> findAll() {
        return jpaTechnology.findAll();
    }

    @Override
    public List<Technology> findAllByIds(Set<UUID> ids) {
        return jpaTechnology.findAllById(ids);
    }

    @Override
    public List<Technology> findAllByFieldId(UUID fieldId) {
        return jpaTechnology.findAllByFieldId(fieldId);
    }

    @Override
    public void deleteById(UUID id) {
        jpaTechnology.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaTechnology.existsByName(name);
    }

    @Override
    public List<Technology> findAllByNames(Collection<String> names) {
        return jpaTechnology.findAllByNameIn(names);
    }

    @Override
    public Optional<Technology> findTechnologyByName(String technologyName) {
        return jpaTechnology.findTechnologyByName(technologyName);
    }

}
