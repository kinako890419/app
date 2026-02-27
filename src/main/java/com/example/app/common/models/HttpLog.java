package com.example.app.common.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class HttpLog {
    private String requestId;
    private String method;
    private String uri;
    private String queryParams;
    private String requestBody;
    private int responseStatus;
    private String responseBody;
}
