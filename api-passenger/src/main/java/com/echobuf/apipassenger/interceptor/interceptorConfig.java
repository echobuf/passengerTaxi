package com.echobuf.apipassenger.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
@Configuration
public class interceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/noauthTest")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check");
    }
}
