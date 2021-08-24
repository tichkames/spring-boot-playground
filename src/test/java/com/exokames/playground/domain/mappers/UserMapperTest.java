package com.exokames.playground.domain.mappers;

import com.exokames.playground.data.dto.UserDto;
import com.exokames.playground.domain.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserMapperTest {

    static final String userFile = "/json/user.json";
    static UserDto userDto;

    @Test
    void userDtoToUser() {
        //given
        userDto = getUser(userFile);

        //when
        User user = UserMapper.INSTANCE.modelToEntity(userDto);

        //then
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getAddress().getStreet(), user.getAddress().getStreet());
        assertEquals(userDto.getCompany().getName(), user.getCompany().getName());
    }

    static UserDto getUser(String path) {
        ObjectMapper mapper = new ObjectMapper();
        var typeReference = new TypeReference<UserDto>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        try {
            UserDto user = mapper.readValue(inputStream, typeReference);
            log.info(String.format("Test User Retrieved\n %s", user));
            return user;
        } catch (IOException e) {
            log.error("Unable to load user: " + e.getMessage());
        }
        return null;
    }
}
