package com.arz.pmp.base.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.arz.pmp.base.api.interceptor.LoginAuthInterceptor;

/**
 * description: 拦截器注册
 *
 * @author chen wei
 * @date 2019/7/12 14:46
 * @version: 1.0
 *           <p>
 *           Copyright: Copyright (c) 2019
 *           </p>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginAuthInterceptor());
    }

    @Bean
    public LoginAuthInterceptor loginAuthInterceptor() {

        return new LoginAuthInterceptor();
    }
}
