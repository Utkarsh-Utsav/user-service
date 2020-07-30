package dev.utkarsh.userservice.controller;

import dev.utkarsh.userservice.dto.ResponseDto;
import dev.utkarsh.userservice.dto.UserDto;
import dev.utkarsh.userservice.dto.UserResponseDto;
import dev.utkarsh.userservice.model.User;
import dev.utkarsh.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseDto<UserResponseDto> registerUser(@RequestBody UserDto userDto){
        User user = userService.registerUser(userDto);
        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(), user.getFullName(),user.getEmail(),user.isActive())
        );
    }
    @GetMapping("/user/confirm")
    public  ResponseDto<UserResponseDto> validateUser(@RequestParam String token){
        User user = userService.validateUser(token);
        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(), user.getFullName(),user.getEmail(),user.isActive())
        );
    }
}
