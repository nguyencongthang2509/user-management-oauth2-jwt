package com.example.user_management.infrastructure.security;

import com.example.user_management.entity.User;
import com.example.user_management.entity.UserToken;
import com.example.user_management.infrastructure.constant.Role;
import com.example.user_management.repository.UserTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "QHMBQfsViR66wU3Yx/MOdkKcHdmJeRy4JdbDbrjmZdfu35Q7yzH6b3vJCrQcNgoOEFfsGyhOeF5Pby7R+YzG0w==";
    private final long jwtExpirationInMs = 86400000; // 24 giờ

    @Autowired
    private UserTokenRepository userTokenRepository;

    public String generateTokenUser(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 10000);
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        UserToken userToken = new UserToken();
        userToken.setUserId(user.getId());
        userToken.setToken(token);
        userToken.setExpiredAt(expiryDate.getTime());
        userTokenRepository.save(userToken);
        return token;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        String role = claims.get("role", String.class);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        User principal = new User();
        principal.setEmail(email);
        principal.setRole(Role.valueOf(authority.getAuthority()));
        return new UsernamePasswordAuthenticationToken(principal, token, Collections.singletonList(authority));
    }

    public boolean validateToken(String token) {
        try {
            // Parse token và kiểm tra chữ ký
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token);

            // Lấy thời gian hết hạn từ token
            Date expirationDate = claims.getBody().getExpiration();
            // Kiểm tra xem token có hết hạn chưa
            return expirationDate.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            // Xử lý lỗi khi parse hoặc kiểm tra token
            return false;
        }
    }
}
