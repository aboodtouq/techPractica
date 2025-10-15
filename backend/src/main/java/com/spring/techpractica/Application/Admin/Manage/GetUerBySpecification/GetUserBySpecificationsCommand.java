package com.spring.techpractica.Application.Admin.Manage.GetUerBySpecification;

public record GetUserBySpecificationsCommand(String userName,String role,
                                             int page,int size) {
}
