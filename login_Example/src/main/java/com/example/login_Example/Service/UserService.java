package com.example.login_Example.Service;

import com.example.login_Example.dtos.CredentialsDto;
import com.example.login_Example.dtos.SignUpDto;
import com.example.login_Example.dtos.UserDto;
import com.example.login_Example.Entity.Users;
import com.example.login_Example.Exeptions.AppException;
import com.example.login_Example.Mapper.UserMapper;
import com.example.login_Example.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public ResponseEntity<UserDto> login(CredentialsDto credentialsDto) {
        Users user = userRepo.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            UserDto userDto = userMapper.toUserDto(user);
            return ResponseEntity.ok().body(userDto);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<Users> optionalUser = userRepo.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Users user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        Users savedUser = userRepo.save(user);

        return userMapper.toUserDto(savedUser);
    }
}
