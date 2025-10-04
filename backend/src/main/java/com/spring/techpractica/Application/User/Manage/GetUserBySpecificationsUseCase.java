package com.spring.techpractica.Application.User.Manage;

import com.spring.techpractica.Core.User.User;
import com.spring.techpractica.Core.User.UserRepository;
import com.spring.techpractica.infrastructure.Jpa.User.UserSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserBySpecificationsUseCase {

    private final UserRepository userRepository;
    private final UserSpecifications  userSpecifications;

    public List<User> execute(GetUserBySpecificationsCommand command){
        Specification<User> specification = userSpecifications
                .buildDynamicSpecification(command.userName(), command.role());

        return userRepository.findAllBySpecifications(specification);
    }
}