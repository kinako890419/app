package com.example.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResp {

    @JsonProperty("token")
    private String token;

    @JsonProperty("user")
    private UserProfileResp userProfile;

}
