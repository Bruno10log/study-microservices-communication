package com.bruno10log.productapi.modules.jwt.service;

import com.bruno10log.productapi.config.exception.AuthenticationException;
import com.bruno10log.productapi.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static io.jsonwebtoken.lang.Objects.isEmpty;

@Service
public class JwtService {

    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public boolean isAuthorized(String token) {

        var accessToken = extractToken(token);
        try {
            var claims = Jwts.parser().setSigningKey(Keys
                                      .hmacShaKeyFor(apiSecret.getBytes()))
                                      .build().parseClaimsJws(accessToken)
                                      .getBody();

            var user = JwtResponse.getUser(claims);

            if(isEmpty(user) || isEmpty(user.id())) {
                throw new AuthenticationException("The user is not valid.");
            }

            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new AuthenticationException("Error while trying to process the Access Token.");
        }
    }

    private String extractToken(String token) {
        if(isEmpty(token)) {
            throw new AuthenticationException("The access token was not informed.");
        }

        if(token.contains(EMPTY_SPACE)) {
            return token.split(EMPTY_SPACE)[TOKEN_INDEX];
        }

        return token;
    }
}
