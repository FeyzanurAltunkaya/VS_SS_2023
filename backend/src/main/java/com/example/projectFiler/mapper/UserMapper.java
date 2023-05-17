package com.example.projectFiler.mapper;

import com.example.projectFiler.dto.UserDto;
import com.example.projectFiler.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserEntity toEntity(UserDto userDto);

  UserDto toDto(UserEntity userEntity);
}
