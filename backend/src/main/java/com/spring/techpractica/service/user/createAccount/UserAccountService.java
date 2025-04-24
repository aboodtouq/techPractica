package com.spring.techpractica.service.user.createAccount;

import com.spring.techpractica.dto.userRegestation.UserCreateAccount;
import com.spring.techpractica.factory.UserFactory;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.service.user.UserManagementData;
import com.spring.techpractica.service.user.createAccount.validatorCreateAccount.ValidatorCreateAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class responsible for managing the creation of user accounts.
 * <p>
 * This class follows the Open/Closed Principle by utilizing a list of validators that can be extended without modifying
 * the service logic. Validators are automatically injected by Spring and applied during account creation to ensure that
 * the data is valid before persistence.
 * </p>
 * <p>
 * Responsibilities:
 * <ul>
 *     <li>Run custom validators for user registration (email, username, etc.)</li>
 *     <li>Map registration DTO to User entity</li>
 *     <li>Encode the user's password securely</li>
 *     <li>Save the user to the data source</li>
 * </ul>
 */
@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserManagementData userManagementData;
    private final List<ValidatorCreateAccount> validators;
    private final UserFactory userFactory;

    /**
     * Creates a new user account. This method:
     * <ol>
     *     <li>Validates the user data using all registered validators</li>
     *     <li>Maps the input DTO to a {@link User} entity</li>
     *     <li>Encodes the user's password</li>
     *     <li>Persists the user entity</li>
     * </ol>
     *
     * @param userCreateAccount the user registration data to create the account
     * @throws com.spring.techpractica.exception.UserAlreadyExistsException if any validation fails (e.g., email or username already exists)
     */
    @Transactional
    public void createAccount(UserCreateAccount userCreateAccount) {
        validators.forEach(v -> v.validate(userCreateAccount));
        User user = prepareUserForSaving(userCreateAccount);
        userManagementData.saveUser(user);
    }

    /**
     * Maps the registration DTO to a {@link User} entity and encodes the password.
     *
     * @param userCreateAccount the registration data
     * @return a {@link User} entity ready for persistence
     */
    private User prepareUserForSaving(UserCreateAccount userCreateAccount) {
        return userFactory.createFrom(userCreateAccount);
    }
}
