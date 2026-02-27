package com.example.app.controller;

import com.example.app.common.models.RespMsg;
import com.example.app.model.LoginResp;
import com.example.app.model.UserLoginReqDto;
import com.example.app.model.UserRegisterReqDto;
import com.example.app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RespMsg register(@Valid @RequestBody UserRegisterReqDto req) {
        return authService.userRegister(req);
    }

    @PostMapping("/login")
    public LoginResp login(@Valid @RequestBody UserLoginReqDto req) {
        return authService.userLogin(req);
    }

}
