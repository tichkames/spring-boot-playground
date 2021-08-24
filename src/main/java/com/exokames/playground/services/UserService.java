package com.exokames.playground.services;

import com.exokames.playground.constants.UrlConstants;
import com.exokames.playground.data.dto.UserDto;
import com.exokames.playground.domain.exceptions.UserNotFoundException;
import com.exokames.playground.domain.mappers.UserMapper;
import com.exokames.playground.domain.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserMapper userMapper;

    public UserDto saveUser(UserDto userDto) {
        log.info(String.format("Saving user: %s", userDto));
        return userMapper.entityToModel(userRepository.save(userMapper.modelToEntity(userDto)));
    }

    public List<UserDto> getAllUsers() {
        log.info(String.format("Fetching all users!"));
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.entityToModel(user))
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        log.info(String.format("Fetching user for id: %s", id));
        return userRepository.findById(id)
                .map((user) -> userMapper.entityToModel(user))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto fetchRemoteUser(Long id) {
        log.info(String.format("Fetching remote user for id: %s", id));
        final var url = UrlConstants.base_url + "/users/" + id;
        log.debug(String.format("full url: %s", url));
        final var response = restTemplate.getForEntity(url, UserDto.class);
        return response.getBody();
    }

    public List<UserDto> fetchRemoteUsers() {
        log.info("Fetching all remote users!");
        final var url = UrlConstants.base_url + "/users";
        log.debug(String.format("full url: %s", url));
        final var response = restTemplate.getForObject(url, UserDto[].class);
        return Arrays.asList(response);
    }
}
