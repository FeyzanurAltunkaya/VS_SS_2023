package com.example.projectFiler.mapper;

import org.mapstruct.Mapper;

import com.example.projectFiler.dto.UserDto;
import com.example.projectFiler.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);
}
