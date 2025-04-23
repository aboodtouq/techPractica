package com.spring.techpractica.service.user;

import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing user data operations such as saving, deleting,
 * and retrieving users by email or username.
 */
@Service
public class UserManagementData {

    private final UserRepository userRepository;

    /**
     * Constructor to inject the {@link UserRepository} dependency.
     *
     * @param userRepository The repository used for user data operations.
     */
    @Autowired
    public UserManagementData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves the provided user entity to the database.
     *
     * @param user The user entity to be saved.
     * @return The saved user entity.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes the provided user entity from the database.
     *
     * @param user The user entity to be deleted.
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /**
     * Retrieves a user by their email. If the user is not found, it throws
     * a {@link ResourcesNotFoundException}.
     *
     * @param email The email of the user to retrieve.
     * @return The user entity associated with the provided email.
     * @throws ResourcesNotFoundException If no user is found with the provided email.
     */
    public User getUserByEmail(String email) {
        return userRepository
                .findUserByUserEmail(email)
                .orElseThrow(() ->
                        new ResourcesNotFoundException("User not found with email: " + email));
    }

    /**
     * Retrieves a user by their email. This method returns an {@link Optional}
     * that may be empty if no user is found.
     *
     * @param email The email of the user to retrieve.
     * @return An {@link Optional} containing the user if found, or empty if not.
     */
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }

    /**
     * Retrieves a user by their username. If the user is not found, it throws
     * a {@link ResourcesNotFoundException}.
     *
     * @param username The username of the user to retrieve.
     * @return The user entity associated with the provided username.
     * @throws ResourcesNotFoundException If no user is found with the provided username.
     */
    public User getUserByUsername(String username) {
        return userRepository
                .findUserByUserName(username)
                .orElseThrow(() ->
                        new ResourcesNotFoundException("User not found with username: " + username));
    }

    /**
     * Retrieves a user by their username. This method returns an {@link Optional}
     * that may be empty if no user is found.
     *
     * @param username The username of the user to retrieve.
     * @return An {@link Optional} containing the user if found, or empty if not.
     */
    public Optional<User> findUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }
}
