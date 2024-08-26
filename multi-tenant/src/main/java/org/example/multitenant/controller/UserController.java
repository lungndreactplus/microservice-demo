package org.example.multitenant.controller;

import lombok.RequiredArgsConstructor;
import org.example.multitenant.dto.UserRequest;
import org.example.multitenant.dto.UserResponse;
import org.example.multitenant.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateUser(@RequestBody UserRequest user){
        userService.createUser(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }
}
