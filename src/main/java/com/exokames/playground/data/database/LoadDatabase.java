package com.exokames.playground.data.database;

import com.exokames.playground.data.dto.UserDto;
import com.exokames.playground.domain.repositories.UserRepository;
import com.exokames.playground.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {
    static final String usersFile = "/json/users.json";

    @Bean
    CommandLineRunner initDatabase(UserService userService) {
        return args -> {
            log.info("Pre-loading users into database");
            getUsers(usersFile).forEach(user -> userService.saveUser(user));
        };
    }

    List<UserDto> getUsers(String path) {
        ObjectMapper mapper = new ObjectMapper();
        var typeReference = new TypeReference<List<UserDto>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        try {
            List<UserDto> users = mapper.readValue(inputStream, typeReference);
            log.info(String.format("Test users retrieved from file: %s", users));
            return users;
        } catch (IOException e) {
            log.error("Unable to load users: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
