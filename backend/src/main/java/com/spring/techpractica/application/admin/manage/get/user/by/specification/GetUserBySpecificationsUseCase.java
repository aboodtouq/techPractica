package com.spring.techpractica.application.admin.manage.get.user.by.specification;

import com.spring.techpractica.core.User.AccountStatus;
import com.spring.techpractica.core.User.User;
import com.spring.techpractica.core.User.UserRepository;
import com.spring.techpractica.infrastructure.Jpa.User.UserSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserBySpecificationsUseCase {

    private final UserRepository userRepository;

    public List<User> execute(GetUserBySpecificationsCommand command){
        Specification<User> specification = UserSpecifications
                .buildDynamicSpecification(command.userName(), command.role(), AccountStatus.COMPLETE_PROFILE);

        return userRepository.findAllBySpecifications(specification,
                PageRequest.of(command.page(), command.size()));
    }
}