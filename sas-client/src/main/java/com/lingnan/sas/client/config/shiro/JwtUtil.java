package com.lingnan.sas.client.config.shiro;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author 19399.
 * @date 2021/3/24.
 * @time 23:42
 */
//加密Token 和 验证Token
@Component
@Slf4j
public class JwtUtil {
    @Value("${sas.jwt.secret}")
    private String secret;

    @Value("${sas.jwt.expire}")
    private int expire;

    public String createToken(int userId) {
        //过期时间为5天
        Date date = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 5);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create();
        String token = builder.withClaim("userId", userId).withExpiresAt(date).sign(algorithm);
        return token;
    }

    public int getUserId(String token) {
        //解码对象
        DecodedJWT jwt = JWT.decode(token);
        int userId = jwt.getClaim("userId").asInt();
        return userId;
    }
    public void verifierToken(String token){
        //创建算法对象
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //构建验证对象
        JWTVerifier verifier = JWT.require(algorithm).build();
        //验证token是不是过期或者是这个秘钥，不是会抛出runtime异常
        verifier.verify(token);
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public String getPassword(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("password").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
