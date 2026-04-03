package com.auth_app.services;

import com.auth_app.Auth.Payload.UserDto;

public interface UserService {

    //create user
    UserDto createUser(UserDto userDto);

    //get user by email
    UserDto getUserByEmail(String email);

    //update user
    UserDto updateUser(UserDto userDto,String userId);

    //delete user
    void deleteUser(String userId);

    //get all users
    Iterable<UserDto> getAllUsers();

    //get user by id
    UserDto getUserById(String userId);

}
