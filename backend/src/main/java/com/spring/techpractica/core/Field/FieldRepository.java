package com.spring.techpractica.core.Field;

import com.spring.techpractica.core.Field.Entity.Field;
import com.spring.techpractica.core.Shared.BaseRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FieldRepository extends BaseRepository<Field> {
    boolean existsByName(String name);

    List<Field> findAllByNames(Collection<String> names);

    Optional<Field> findFieldByName(String fieldName);

    List<Field> findAll();

    void deleteById(UUID id);
}
