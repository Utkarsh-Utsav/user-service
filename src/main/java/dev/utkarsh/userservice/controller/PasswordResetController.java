package dev.utkarsh.userservice.controller;

import dev.utkarsh.userservice.dto.PasswordDto;
import dev.utkarsh.userservice.dto.ResponseDto;
import dev.utkarsh.userservice.dto.UserResponseDto;
import dev.utkarsh.userservice.model.MyUserDetail;
import dev.utkarsh.userservice.model.User;
import dev.utkarsh.userservice.model.VerificationToken;
import dev.utkarsh.userservice.repository.UserRepository;
import dev.utkarsh.userservice.repository.VerificationTokenRepository;
import dev.utkarsh.userservice.service.MyUserDetailService;
import dev.utkarsh.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class PasswordResetController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @GetMapping ("/user/reset")
    ResponseDto<UserResponseDto> resetPassword(@RequestParam  String email){
        User user =  userRepository.findByEmail(email);
        userService.generateResetToken(user);
        return  new ResponseDto<>(
              HttpStatus.OK,
              new UserResponseDto(user.getId(), user.getFullName(),user.getEmail(),user.isActive())
        );
    }
    @PostMapping("/user/newpassword")
    ResponseDto<UserResponseDto> newPassword(@RequestBody PasswordDto passwordDto){
        User user = userService.newPassword(passwordDto);
        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(),user.isActive())
        );

    }

}
