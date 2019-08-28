package com.mall.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA256(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * Created by macro on 2018/4/26.
 */
@Component
@Slf4j
public class JwtTokenUtils {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /*
    * @param claims 负载
    * 根据负载生成Jwt的token
    */
    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    /*
    * @param token 客户端传入的token
    * 从token中获取JWT中的负载
    */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex){
            log.error("JWT格式验证失败:{}", ex.getMessage());
        }

        return claims;
    }

    /*
    * 生成token的过期时间
    *
    */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /*
    * @param token 客户端传入的token
    * 从token中获取登录的用户名
    */
    public String getUserNameFromToken(String token){
        String userName;
        try {
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject();
        }catch (Exception ex){
            userName = null;
        }

        return userName;
    }

    /*
    * 验证token是否还有效
    *
    * @param token 客户端传入的token
    * @param userDetails 从数据库中查询出来的用户信息
    */
    public Boolean validateToken(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /*
    * 判断token是否失效
    */
    private Boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /*
    * 从token中获取过期时间
    */
    private Date getExpiredDateFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /*
    * 根据用户信息生成token
    * @param userDetails 从数据库查询出来的用户信息
    */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);
    }

    /*
    * @param token 客户端传递过来的token
    * 判断token是否可以刷新
    */
    public Boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /*
    * @param token 客户端传递过来的token
    * 刷新token
    */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
