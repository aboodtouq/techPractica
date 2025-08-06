package com.spring.techpractica.infrastructure.Jpa.User;

import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class JpaUserRepository implements UserRepository {
    private final JpaUser jpaUser;

    @Override
    @Transactional
    public User save(User user) {
        return jpaUser.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUser.existsByEmail(email);
    }
}
