package com.bruno10log.productapi.config.interceptor;

import com.bruno10log.productapi.modules.jwt.service.JwtService;
import feign.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;


public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(isOptions(request)) {
            return true;
        }

        var authorization = request.getHeader(AUTHORIZATION);
        jwtService.isAuthorized(authorization);

        return true;
    }

    private boolean isOptions(HttpServletRequest request) {
        return Request.HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
}
