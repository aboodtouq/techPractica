package com.spring.techpractica.service.user;

import com.spring.techpractica.dto.userRegestation.UserCreateAccount;
import com.spring.techpractica.exception.UserAlreadyExistsException;
import com.spring.techpractica.maper.UserMapper;
import com.spring.techpractica.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing user account creation, including validation of unique email
 * and username, as well as preparing the user entity for persistence.
 */
@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserManagementData userManagementData;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    /**
     * Creates a new user account. This method validates the user data, checks for existing
     * email and username, and then saves the user.
     *
     * @param userCreateAccount The user registration details to be used for account creation.
     */
    @Transactional
    public void createAccount(UserCreateAccount userCreateAccount) {
        validateNewUser(userCreateAccount);f
        User user = prepareUserForSaving(userCreateAccount);
        userManagementData.saveUser(user);
    }

    /**
     * Validates the provided user details. Ensures that the email and username are not
     * already in use.
     *
     * @param userCreateAccount The user details to validate.
     */
    private void validateNewUser(UserCreateAccount userCreateAccount) {
        checkEmailNotUsed(userCreateAccount.getUserEmail());
        checkUsernameNotUsed(userCreateAccount.getName());
    }

    /**
     * Checks if the provided email is already in use by another user. If the email is already
     * taken, throws a {@link UserAlreadyExistsException}.
     *
     * @param email The email to check for uniqueness.
     * @throws UserAlreadyExistsException If the email is already in use.
     */
    private void checkEmailNotUsed(String email) {
        userManagementData.findUserByEmail(email)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Email is already in use");
                });
    }

    /**
     * Checks if the provided username is already in use by another user. If the username is
     * already taken, throws a {@link UserAlreadyExistsException}.
     *
     * @param username The username to check for uniqueness.
     * @throws UserAlreadyExistsException If the username is already in use.
     */
    private void checkUsernameNotUsed(String username) {
        userManagementData.findUserByUserName(username)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Username is already in use");
                });
    }

    /**
     * Prepares the user entity for persistence by encoding the password and mapping the
     * registration data to the user entity.
     *
     * @param userCreateAccount The user details for account creation.
     * @return The user entity prepared for saving.
     */
    private User prepareUserForSaving(UserCreateAccount userCreateAccount) {
        String encodedPassword = passwordEncoder.encode(userCreateAccount.getUserPassword());
        User user = userMapper.userCreateAccountToUser(userCreateAccount);
        user.setUserPassword(encodedPassword);
        return user;
    }
}
