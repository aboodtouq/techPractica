package com.spring.techpractica.service.user.createAccount.validatorCreateAccount;

import com.spring.techpractica.dto.userRegestation.UserCreateAccount;
import com.spring.techpractica.exception.UserAlreadyExistsException;
import com.spring.techpractica.service.user.UserManagementData;
import org.springframework.stereotype.Component;

/**
 * Abstract base class for user account creation validators.
 *
 * <p>This class defines a contract for validating parts of a user registration request.
 * Implementations should override {@link #validate(UserCreateAccount)} to perform specific
 * checks such as ensuring email uniqueness, username availability, or password strength.</p>
 *
 * <p><b>Design Principle:</b> This design follows the <i>Open/Closed Principle</i> (SOLID).
 * New validation rules can be added without modifying existing logic. Simply extend this
 * class and annotate your implementation with {@code @Component} — Spring will automatically
 * inject all beans into the service that uses the {@link java.util.List} of validators.</p>
 */
@Component
public abstract class ValidatorCreateAccount {

    protected final UserManagementData userManagementData;

    protected ValidatorCreateAccount(UserManagementData userManagementData) {
        this.userManagementData = userManagementData;
    }

    /**
     * Performs validation logic on the given user creation data.
     *
     * @param userCreateAccount The registration details to validate.
     * @throws UserAlreadyExistsException if validation fails due to duplication or conflict.
     */
    public abstract void validate(UserCreateAccount userCreateAccount) throws UserAlreadyExistsException;
}
