package com.spring.techpractica.maper;

import com.spring.techpractica.dto.user.UserCreateAccount;
import com.spring.techpractica.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "userName", source = "userCreateAccount.name")
    @Mapping(target = "userFirstName", source = "userCreateAccount.firstName")
    @Mapping(target = "userLastName", source = "userCreateAccount.lastName")
    @Mapping(target = "userEmail", source = "userCreateAccount.userEmail")
    User userCreateAccountToUser(UserCreateAccount userCreateAccount);
}
