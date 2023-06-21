package com.example.talken.user.service;

import com.example.talken.common.Status;
import com.example.talken.user.dto.UserRequestDto;
import com.example.talken.user.entity.User;
import com.example.talken.user.exception.UserError;
import com.example.talken.user.exception.UserException;
import com.example.talken.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static Status.Auth isAuthenticated = Status.Auth.UNAUTHENTICATED;
    private static Status.User isDeleted = Status.User.ALIVE;

    private final UserRepository userRepository;

    @Transactional
    public void signup(UserRequestDto.Signup request) {
        String email = request.getEmail();

        userRepository.findByEmail(email).ifPresent(user -> {
                    throw new UserException(UserError.DUPLICATE_EMAIL);
        });

        userRepository.save(request.toEntity(isAuthenticated, isDeleted));
    }

    public void login(UserRequestDto.Login request) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserError.USER_NOT_FOUND)
        );

        if(!password.equals(user.getPassword())) {
            throw new UserException(UserError.INVALID_PASSWORD);
        }
    }
}
