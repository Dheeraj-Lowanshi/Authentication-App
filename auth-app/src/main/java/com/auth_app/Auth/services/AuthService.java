package com.auth_app.services;

import com.auth_app.Auth.Payload.UserDto;

public interface AuthService {

    UserDto registerUser(UserDto userDto);

    //login user
}
