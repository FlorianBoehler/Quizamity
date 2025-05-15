package com.quizamity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserUpdateDto {

    @NotBlank
    public String username;

    @Email
    public String email;

    public String password;

    @NotBlank
    public String roleName;
}
