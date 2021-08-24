package com.exokames.playground.services;

import com.exokames.playground.data.dto.UserDto;
import com.exokames.playground.domain.exceptions.UserNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    UserService userService;

    static final String userFile = "/json/user.json";
    static List<UserDto> users;

    @BeforeAll
    static void setUp() {
    }

    @Test
    void getAllUsers() {
        var users = userService.getAllUsers();
        users.forEach(user -> log.info(user.toString()));
        assertTrue(!users.isEmpty());
    }

    @Test
    void testGetUser() {
        var user = userService.getUser(Long.valueOf(1));
        log.info(String.valueOf(user));
        assertNotNull(user);
    }

    @Test
    void testGetUserException() {
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.getUser(Long.valueOf(100)), "Didn't throw exception");
        assertTrue(exception.getMessage().contains("Could not find user"));
    }

    @Test
    void saveUser() {
        userService.saveUser(getUser(userFile));

        final List users = userService.getAllUsers();
        users.forEach(System.out::println);

        Assert.notEmpty(users);
    }

    @Test
    void fetchRemoteUser() {
        userService.fetchRemoteUser(Long.valueOf(1));
    }

    @Test
    void fetchAllUsers() {
        userService.fetchRemoteUsers().forEach(System.out::println);
    }

    static UserDto getUser(String path) {
        ObjectMapper mapper = new ObjectMapper();
        var typeReference = new TypeReference<UserDto>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        try {
            UserDto user = mapper.readValue(inputStream, typeReference);
            log.info(String.format("Test User Retrieved: %s", user));
            return user;
        } catch (IOException e) {
            log.error("Unable to load user: " + e.getMessage());
        }
        return null;
    }
}
