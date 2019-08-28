package com.mall.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableCaching
@MapperScan("com.mall.admin.mapper")
public class MallAdminApplication {

    @PostConstruct
    void start(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static void main(String[] args){
        SpringApplication.run(MallAdminApplication.class);
    }
}
