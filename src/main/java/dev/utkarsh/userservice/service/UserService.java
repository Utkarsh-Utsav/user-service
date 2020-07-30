package dev.utkarsh.userservice.service;

import dev.utkarsh.userservice.dto.PasswordDto;
import dev.utkarsh.userservice.dto.UserDto;
import dev.utkarsh.userservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User registerUser(UserDto userDto);
    public User validateUser(String token);
    public void generateResetToken(User user);
    public User newPassword(PasswordDto passwordDto);
}
