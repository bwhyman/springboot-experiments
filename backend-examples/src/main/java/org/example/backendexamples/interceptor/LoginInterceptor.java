package org.example.backendexamples.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backendexamples.component.JWTComponent;
import org.example.backendexamples.exception.Code;
import org.example.backendexamples.exception.XException;
import org.example.backendexamples.vo.RequestAttributeConst;
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
        long uid = decodedJWT.getClaim(RequestAttributeConst.UID).asLong();
        String role = decodedJWT.getClaim(RequestAttributeConst.ROLE).asString();

        request.setAttribute(RequestAttributeConst.UID, uid);
        request.setAttribute(RequestAttributeConst.ROLE, role);
        return true;
    }
}
