package com.exokames.playground.data.controllers;

import com.exokames.playground.data.dto.UserDto;
import com.exokames.playground.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    ResponseEntity<List<UserDto>> users() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDto> user(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/users")
    UserDto newUser(@RequestBody UserDto newUser) {
        return userService.saveUser(newUser);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/remote/users")
    ResponseEntity<List<UserDto>> remoteUsers() {
        return ResponseEntity.ok(userService.fetchRemoteUsers());
    }

    @GetMapping("/remote/users/{id}")
    ResponseEntity<UserDto> remoteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.fetchRemoteUser(id));
    }
}
