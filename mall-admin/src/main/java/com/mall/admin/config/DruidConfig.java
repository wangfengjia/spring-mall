package com.mall.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    //配置druid的监控,需要配置servlet和filter
    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean registrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        registrationBean.setInitParameters(initParams);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean =
                new FilterRegistrationBean(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", ".html");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        return filterRegistrationBean;
    }
}
