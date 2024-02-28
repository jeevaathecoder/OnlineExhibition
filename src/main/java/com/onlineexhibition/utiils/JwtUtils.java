package com.onlineexhibition.utiils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private String secret="11212ss";  //取出配置文件的密码值

    public  String createToken(String userId){
        Date date= DateUtil.offset(new Date(), DateField.DAY_OF_YEAR,5);
        Algorithm algorithm=Algorithm.HMAC256(secret);
        JWTCreator.Builder builder= JWT.create();
        String token=builder.withClaim("userId",userId).withExpiresAt(date).sign(algorithm);
        return token;
    }

    public int getUserId(String token){
        DecodedJWT jwt= JWT.decode(token);
        int userId=jwt.getClaim("userId").asInt();
        return userId;
    }

    public boolean verifierToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true; // 令牌验证成功
        } catch (JWTVerificationException e) {
            // 令牌验证失败
            return false;
        }
    }




}
