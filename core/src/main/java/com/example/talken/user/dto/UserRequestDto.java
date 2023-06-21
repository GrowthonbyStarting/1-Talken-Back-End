package com.example.talken.user.dto;

import com.example.talken.common.CommonConstant;
import com.example.talken.common.Status;
import com.example.talken.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRequestDto {

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Signup {

        @NotNull
        @Pattern(regexp = CommonConstant.RegExp.EMAIL)
        String email;

        @NotNull
        String username;

        @NotNull
        String password;

        @NotNull
        @Pattern(regexp = CommonConstant.RegExp.PHONE)
        String phone;

        @NotNull
        String role;

        public User toEntity(Status.Auth isAuthenticated, Status.User isDeleted) {
            return User.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .phone(phone)
                    .role(role)
                    .isAuthenticated(isAuthenticated)
                    .isDeleted(isDeleted)
                    .build();
        }
    }

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Login {

        @NotNull
        @Pattern(regexp = CommonConstant.RegExp.EMAIL)
        String email;

        @NotNull
        String password;
    }
}
