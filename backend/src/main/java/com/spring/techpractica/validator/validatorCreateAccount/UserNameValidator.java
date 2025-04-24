package com.spring.techpractica.validator.validatorCreateAccount;

import com.spring.techpractica.dto.userRegestation.UserCreateAccount;
import com.spring.techpractica.exception.UserAlreadyExistsException;
import com.spring.techpractica.service.user.createAccount.UserAccountService;
import com.spring.techpractica.service.user.UserManagementData;
import org.springframework.stereotype.Component;

/**
 * Validator for checking the uniqueness of a user's username during registration.
 *
 * <p>This class is a concrete implementation of {@link CreateAccountValidator} that checks
 * whether the provided username already exists in the system. If the username is already in use,
 * it throws a {@link UserAlreadyExistsException} with a specific error message.</p>
 *
 * <p><b>Design Principle:</b> This class adheres to the <i>Open/Closed Principle</i> by
 * allowing new validation rules to be added without modifying this class, enabling easy
 * extensibility and maintainability.</p>
 *
 * <p>Spring will automatically inject this component into the list of validators used
 * in {@link UserAccountService}.</p>
 */
@Component
public class UserNameValidator extends CreateAccountValidator {

    protected UserNameValidator(UserManagementData userManagementData) {
        super(userManagementData);
    }

    /**
     * Validates that the username provided during user registration is unique.
     *
     * @param userCreateAccount The user registration details to validate.
     * @throws UserAlreadyExistsException if the username is already in use by another user.
     */
    @Override
    public void validate(UserCreateAccount userCreateAccount) {
        userManagementData.findUserByUserName(userCreateAccount.getName())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("UserName is already in use");
                });
    }
}
