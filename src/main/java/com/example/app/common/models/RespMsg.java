package com.example.app.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespMsg {
    @JsonProperty("status_type")
    private String status;

    @JsonProperty("response_message")
    private String message;
}
