package com.example.login_Example.Mapper;

import com.example.login_Example.dtos.SignUpDto;
import com.example.login_Example.dtos.UserDto;
import com.example.login_Example.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(Users user);

    @Mapping(target = "password", ignore = true)
    Users signUpToUser(SignUpDto signUpDto);

}