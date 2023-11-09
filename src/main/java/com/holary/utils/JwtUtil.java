package com.holary.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

/**
 * @Author: Holary
 * @Date: 2023/11/9 17:17
 * @Description: JWT工具类
 */
public class JwtUtil {
    private static final String KEY = "Holary";

    /**
     * description: 接收业务数据, 生成token并返回
     *
     * @param claims:
     * @return: java.lang.String
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(KEY));
    }

    /**
     * description: 接收token,验证token,并返回业务数据
     *
     * @param token:
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
