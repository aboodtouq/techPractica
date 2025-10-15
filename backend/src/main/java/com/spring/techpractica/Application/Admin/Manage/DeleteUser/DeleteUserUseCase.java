package com.spring.techpractica.Application.Admin.Manage.DeleteUser;

import com.spring.techpractica.Core.User.AccountStatus;
import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public String execute(DeleteUserCommand command){
        User user = userRepository.getOrThrowByID(command.userId());

        user.setDeleted();

        userRepository.update(user);

        return String.format("User %s has been deleted", user.getId());
    }
}
