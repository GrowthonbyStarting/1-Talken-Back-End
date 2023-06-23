package com.example.talken.user.service;

import com.example.talken.common.Status;
import com.example.talken.common.security.jwt.JwtUtil;
import com.example.talken.user.dto.UserRequestDto;
import com.example.talken.user.entity.User;
import com.example.talken.user.exception.UserError;
import com.example.talken.user.exception.UserException;
import com.example.talken.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private static Status.Auth isAuthenticated = Status.Auth.UNAUTHENTICATED;
    private static Status.User isDeleted = Status.User.ALIVE;

    private final PasswordEncoder passwordEncoder;


    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
    public void signup(UserRequestDto.Signup request) {
        String email = request.getEmail();
        String password = request.getPassword();

        userRepository.findByEmail(email).ifPresent(user -> {
                    throw new UserException(UserError.DUPLICATE_EMAIL);
        });

        password = passwordEncoder.encode(password);
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(password)
                .phone(request.getPhone())
                .role(request.getRole())
                .isAuthenticated(isAuthenticated)
                .isDeleted(isDeleted)
                .build();

        userRepository.save(user);
    }

    public void login(UserRequestDto.Login request, HttpServletResponse response) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserError.USER_NOT_FOUND)
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserException(UserError.INVALID_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user));
    }
}
