package com.quizamity.mapper;

import com.quizamity.dto.UserCreateDto;
import com.quizamity.dto.UserResponseDto;
import com.quizamity.dto.UserUpdateDto;
import com.quizamity.model.Role;
import com.quizamity.model.User;
import com.quizamity.security.PasswordService;

public class UserMapper {

    public static User toEntity(UserCreateDto dto, Role role, String hashedPassword) {
        return new User(dto.username, hashedPassword, dto.email, role);
    }

    public static UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.roleName = user.getRole().getName();
        return dto;
    }

    public static void updateEntity(User user, UserUpdateDto dto, Role role, PasswordService passwordService) {
        user.setUsername(dto.username);
        user.setEmail(dto.email);
        user.setRole(role);
        if (dto.password != null && !dto.password.isBlank()) {
            user.setPasswordHash(passwordService.hash(dto.password));
        }
    }

}
