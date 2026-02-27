package com.example.app.service;

import com.example.app.common.models.RespMsg;
import com.example.app.model.LoginResp;
import com.example.app.model.UserLoginReqDto;
import com.example.app.model.UserRegisterReqDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    RespMsg userRegister(UserRegisterReqDto req);
    LoginResp userLogin(UserLoginReqDto req);

}
