package org.example.springmvcexamples.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.springmvcexamples.component.JWTComponent;
import org.example.springmvcexamples.exception.Code;
import org.example.springmvcexamples.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final JWTComponent jwtComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var token = request.getHeader("token");
        if(token == null) {
            throw XException.builder().code(Code.UNAUTHORIZED).build();
        }
        DecodedJWT decodedJWT = jwtComponent.decode(token);
        long uid = decodedJWT.getClaim("uid").asLong();
        String role = decodedJWT.getClaim("role").asString();

        request.setAttribute("uid", uid);
        request.setAttribute("role", role);
        return true;
    }
}
