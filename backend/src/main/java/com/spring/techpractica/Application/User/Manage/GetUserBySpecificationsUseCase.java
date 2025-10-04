package com.spring.techpractica.Application.User.Manage;

import com.spring.techpractica.Core.User.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserBySpecificationsUseCase {

    public List<User> execute(GetUserBySpecificationsCommand command){

    }
}