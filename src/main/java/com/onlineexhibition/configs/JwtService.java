package com.onlineexhibition.configs;

import com.onlineexhibition.model.User;
import com.onlineexhibition.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    private  JwtConfig jwtConfig;
    @Autowired
    private UserRepository userRepository;
    public String extractUsername(String jwt) {
        return  extractClaim(jwt,Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims,T> claimsExtractor) {
        final Claims claims = extractAllClaims(jwt);
        return claimsExtractor.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        extraClaims.put("userId", user.getId());
        extraClaims.put("user_type_id", user.getUser_type_id());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 25))//return to 5
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                .compact();

    }

    public Boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }
    public Boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date(System.currentTimeMillis() - 1000 * 60 * 5));
    }
     public Date extractExpiration(String jwt) {
        return extractClaim(jwt,Claims::getExpiration);
     }
    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyByte = Decoders.BASE64.decode(jwtConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyByte);
    }
}
