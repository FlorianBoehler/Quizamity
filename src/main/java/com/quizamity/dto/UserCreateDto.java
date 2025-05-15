package com.quizamity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserCreateDto {
    @NotBlank
    public String username;

    @NotBlank
    public String password;

    @Email
    public String email;

    @NotBlank
    public String roleName;
}
