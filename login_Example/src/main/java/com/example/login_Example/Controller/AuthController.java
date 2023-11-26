package com.example.login_Example.Controller;

import com.example.login_Example.Config.UserAuthProvider;
import com.example.login_Example.dtos.CredentialsDto;
import com.example.login_Example.dtos.SignUpDto;
import com.example.login_Example.dtos.UserDto;
import com.example.login_Example.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {


        private final UserService userService;
        private final UserAuthProvider authProvider;

        @PostMapping("/login")
        public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
            UserDto userDto = userService.login((credentialsDto)).getBody();
            userDto.setToken(authProvider.createToken(userDto));
            return ResponseEntity.ok(userDto);
        }

        @PostMapping("/register")
        public ResponseEntity<UserDto> register(@RequestBody SignUpDto user) {
            UserDto createdUser = userService.register(user);
            createdUser.setToken(authProvider.createToken(createdUser));
            return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
        }

    }

