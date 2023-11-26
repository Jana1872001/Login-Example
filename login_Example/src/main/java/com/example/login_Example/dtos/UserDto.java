package com.example.login_Example.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String Login;
    private String token;

}
