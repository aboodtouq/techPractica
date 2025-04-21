package com.spring.techpractica.maper;

import com.spring.techpractica.dto.userRegestation.UserCreateAccount;
import com.spring.techpractica.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User userCreateAccountToUser(UserCreateAccount userCreateAccount) {
        return User.builder()
                .userName(userCreateAccount.getName())
                .userFirstName(userCreateAccount.getFirstName())
                .userLastName(userCreateAccount.getLastName())
                .userEmail(userCreateAccount.getUserEmail())
                .build();

    }

}
