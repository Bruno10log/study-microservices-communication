package com.bruno10log.productapi.modules.jwt.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;

public record JwtResponse(Integer id, String name, String email) {
    public static JwtResponse getUser(Claims claims) {
        try {
            return new ObjectMapper().convertValue(claims.get("authUser"), JwtResponse.class);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
