package com.spring.techpractica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateAccount {

    private String name;

    private String firstName;

    private String lastName;

    private String userEmail;

    private String userPassword;

}
