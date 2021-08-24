package com.exokames.playground.domain.mappers;

import com.exokames.playground.data.dto.UserDto;
import com.exokames.playground.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User modelToEntity(UserDto userDto);

    UserDto entityToModel(User user);
}
