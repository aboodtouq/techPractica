package com.spring.techpractica.Core.Shared;

import java.util.UUID;

public interface BaseRepository<T> {
    T save(T t);

    T update(T t);

    T findById(UUID id);
}
