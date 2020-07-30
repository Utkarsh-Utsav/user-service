package dev.utkarsh.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PasswordDto {
    @NotEmpty
    @Size(min = 6)
    @NotEmpty
    @Size(min = 6)
    private  String newPassword;
    private  String token;
}
