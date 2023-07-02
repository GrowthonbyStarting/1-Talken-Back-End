package com.example.talken.user;

import com.example.talken.common.Response;
import com.example.talken.user.dto.TokenDto;
import com.example.talken.user.dto.UserRequestDto;
import com.example.talken.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "사용자 API")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "카카오 소셜 로그인")
    @GetMapping("/user/kakao/callback")
    public ResponseEntity<Response<TokenDto>> kakaoLogin(@Parameter(name = "code") @RequestParam(value = "code") String code) {
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
