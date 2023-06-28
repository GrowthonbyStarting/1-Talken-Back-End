package com.example.talken.user;

import com.example.talken.common.Response;
import com.example.talken.user.dto.TokenDto;
import com.example.talken.user.dto.UserRequestDto;
import com.example.talken.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/kakao/callback")
    public ResponseEntity<Response<TokenDto>> kakaoLogin(@RequestParam(value = "code") String code) {
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<TokenDto>builder()
                        .data(userService.kakaoLogin(code))
                        .build());
    }

    @PostMapping("/users/signup")
    public Response<Void> signup(@RequestBody UserRequestDto.Signup request) {
        userService.signup(request);

        return Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/users/login")
    public Response<Void> login(@RequestBody UserRequestDto.Login request, HttpServletResponse response) {
        userService.login(request, response);

        return Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .build();
    }
}
