package com.auth_app.Auth.services.Impl;

import com.auth_app.Auth.Payload.UserDto;
import com.auth_app.Auth.repositories.RoleRepository;
import com.auth_app.Auth.services.UserService;
import com.auth_app.Auth.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    
    @Override
    public UserDto registerUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userService.createUser(userDto);
    }
}
