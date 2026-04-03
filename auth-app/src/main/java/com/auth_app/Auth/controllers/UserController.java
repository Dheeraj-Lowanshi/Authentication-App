package com.auth_app.Auth.controllers;


import com.auth_app.Auth.Payload.UserDto;
import com.auth_app.Auth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    //create user api
    @PostMapping
    public ResponseEntity<UserDto>createdUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    //get all user api
    @GetMapping
    public ResponseEntity<Iterable<UserDto>>getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    //delete user api
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void>deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    //update user api
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto,@PathVariable String userId){
        return ResponseEntity.ok(userService.updateUser(userDto,userId));
    }

    //get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }





}
