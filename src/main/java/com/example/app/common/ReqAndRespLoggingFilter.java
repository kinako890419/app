package com.example.app.common;

import com.example.app.common.models.HttpLog;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@Slf4j
public class ReqAndRespLoggingFilter extends OncePerRequestFilter {

    private static final String REQUEST_ID_HEADER = "requestId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = request.getHeader(REQUEST_ID_HEADER);
        if (requestId == null || requestId.isBlank()) {
            // todo: request id
            requestId = UUID.randomUUID().toString().substring(0, 8);
        }

        ContentCachingRequestWrapper reqWrapper = new ContentCachingRequestWrapper(request, 0);
        ContentCachingResponseWrapper respWrapper = new ContentCachingResponseWrapper(response);

        try {
            MDC.put("requestId", requestId);
            filterChain.doFilter(reqWrapper, respWrapper);
        } finally {
            HttpLog httpLog = HttpLog.builder()
                    .requestId(requestId)
                    .method(request.getMethod())
                    .uri(request.getRequestURI())
                    .queryParams(request.getQueryString())
                    .requestBody(new String(reqWrapper.getContentAsByteArray(), StandardCharsets.UTF_8))
                    .responseStatus(respWrapper.getStatus())
                    .responseBody(new String(respWrapper.getContentAsByteArray(), StandardCharsets.UTF_8))
                    .build();

            MDC.put("method", httpLog.getMethod());
            MDC.put("uri", httpLog.getUri());
            MDC.put("queryParams", httpLog.getQueryParams() != null ? httpLog.getQueryParams() : "");
            MDC.put("requestBody", httpLog.getRequestBody());
            MDC.put("responseStatus", String.valueOf(httpLog.getResponseStatus()));
            MDC.put("responseBody", httpLog.getResponseBody());

            log.info("{}", httpLog);

            MDC.clear();

            respWrapper.copyBodyToResponse();
        }
    }
}
