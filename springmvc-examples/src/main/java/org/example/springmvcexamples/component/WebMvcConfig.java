package org.example.springmvcexamples.component;

import lombok.RequiredArgsConstructor;
import org.example.springmvcexamples.interceptor.AdminInterceptor;
import org.example.springmvcexamples.interceptor.LoginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/open/**");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");
    }
}
