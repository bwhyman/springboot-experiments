package org.example.backendexamples.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.backendexamples.entity.User;
import org.example.backendexamples.exception.Code;
import org.example.backendexamples.exception.XException;
import org.example.backendexamples.vo.RequestAttributeConst;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var role = request.getAttribute(RequestAttributeConst.ROLE);
        if(!User.ADMIN.equals(role)) {
            throw XException.builder().code(Code.FORBIDDEN).build();
        }
        return true;
    }
}
