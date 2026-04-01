package com.auth_app.services;

import com.auth_app.dtos.UserDto;

public interface AuthService {

    UserDto registerUser(UserDto userDto);

    //login user
}
