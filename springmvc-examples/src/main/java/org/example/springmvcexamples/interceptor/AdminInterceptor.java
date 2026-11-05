package org.example.springmvcexamples.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springmvcexamples.entity.User;
import org.example.springmvcexamples.exception.Code;
import org.example.springmvcexamples.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var role = request.getAttribute("role");
        if(!User.ADMIN.equals(role)) {
            throw XException.builder().code(Code.FORBIDDEN).build();
        }
        return true;
    }
}
