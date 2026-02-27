package com.example.app.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;

    public String generateToken(String subject) {
        log.debug("Generating JWT for subject={}", subject);
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(jwtConfig.getExpirationMs());

        String token = JWT.create()
                .withIssuer(jwtConfig.getIssuer())
                .withSubject(subject)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .sign(algorithm);

        log.info("JWT issued for subject={}, expiresAt={}", subject, expiry);
        return token;
    }

    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        log.debug("Validating JWT token");
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtConfig.getIssuer())
                .build();
        DecodedJWT decoded = verifier.verify(token);
        log.debug("JWT valid for subject={}", decoded.getSubject());
        return decoded;
    }

}
