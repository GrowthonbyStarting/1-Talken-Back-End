package com.example.talken.user.dto;

import jakarta.validation.constraints.NotNull;
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
        String email;

        @NotNull
        String username;

        @NotNull
        String password;

        @NotNull
        String phone;
    }

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Login {

        @NotNull
        String email;

        @NotNull
        String password;
    }
}
