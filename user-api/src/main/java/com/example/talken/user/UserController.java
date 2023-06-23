package com.example.talken.user;

import com.example.talken.common.Response;
import com.example.talken.user.dto.UserRequestDto;
import com.example.talken.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Response<Void> signup(@RequestBody UserRequestDto.Signup request) {
        userService.signup(request);

        return Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/login")
    public Response<Void> login(@RequestBody UserRequestDto.Login request, HttpServletResponse response) {
        userService.login(request, response);

        return Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .build();
    }
}
