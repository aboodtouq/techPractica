package com.spring.techpractica.Application.User.Manage;

public record GetUserBySpecificationsCommand(String userName,String role,
                                             int page,int size) {
}
