package com.mall.admin.config;

import com.mall.common.utils.JwtTokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonModuleConfig {

    @Bean
    public JwtTokenUtils jwtTokenUtils(){
        return new JwtTokenUtils();
    }
}
