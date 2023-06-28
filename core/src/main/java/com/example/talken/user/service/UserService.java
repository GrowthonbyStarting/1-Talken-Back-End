package com.example.talken.user.service;

import com.example.talken.common.Status;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.common.security.jwt.JwtUtil;
import com.example.talken.oauth2.dto.KakaoUserInfo;
import com.example.talken.oauth2.service.Oauth2Service;
import com.example.talken.user.dto.TokenDto;
import com.example.talken.user.dto.UserRequestDto;
import com.example.talken.user.entity.User;
import com.example.talken.user.exception.UserError;
import com.example.talken.user.exception.UserException;
import com.example.talken.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final Oauth2Service oauth2Service;
    private final UserRepository userRepository;


    @Value("spring.security.oauth2.client.registration.kakao.client-secret")
    private String secretKey;

    public TokenDto kakaoLogin(String authorizedCode) {
        KakaoUserInfo userInfo = oauth2Service.getUserInfo(authorizedCode);

        Long kakaoId = userInfo.getId();
        String username = userInfo.getProfileNickname();
        String password = passwordEncoder.encode(kakaoId + secretKey);

        String imageUrl = userInfo.getProfileImageUrl();
        String email = userInfo.getAccountEmail();

        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);
        if (kakaoUser != null) {
            kakaoUser.update(username);
            userRepository.save(kakaoUser);
        } else {
            kakaoUser = User.builder()
                    .email(email)
                    .username(username)
                    .kakaoId(kakaoId)
                    .password(password)
                    .build();
            userRepository.save(kakaoUser);
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(kakaoUser, username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setTOKEN(jwtUtil.createToken(kakaoUser));
        return tokenDto;
    }



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
