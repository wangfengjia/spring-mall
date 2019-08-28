package com.mall.admin.config;

import com.mall.admin.bo.AdminUserDetails;
import com.mall.admin.component.RestAuthenticationEntryPoint;
import com.mall.admin.component.RestfulAccessDeniedHandler;
import com.mall.admin.dto.AdminPermissionResult;
import com.mall.admin.dto.AdminUserResult;
import com.mall.admin.filter.JwtAuthenticationTokenFilter;
import com.mall.admin.service.IAdminUserService;
import com.mall.common.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf() //由于使用的是jwt，所以不需要csrf
                .disable()
                .sessionManagement() //基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*")
                .permitAll()
                .antMatchers("/admin/user/login", "/admin/user/register")
                .permitAll()
                .anyRequest() //除了上面的请求全部需要经过鉴权认证
                .authenticated();

        //禁用缓存
        http.headers().cacheControl();
        //添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录的返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Utils.getMD5(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        //获取登陆用户信息
        return username -> {
            AdminUserResult adminUser = adminUserService.getAdminByUsername(username);
            if (adminUser != null){
                List<AdminPermissionResult> permissionResultList = adminUserService.getPermissionList(adminUser.getId());
                return new AdminUserDetails(adminUser, permissionResultList);
            }
            throw new UsernameNotFoundException("用户名或者密码错误");
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    /*
    * 允许跨域调用的过滤器
    */
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return new CorsFilter(source);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
