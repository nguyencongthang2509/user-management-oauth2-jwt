package com.example.user_management.infrastructure.security;

import com.example.user_management.entity.User;
import com.example.user_management.entity.UserToken;
import com.example.user_management.infrastructure.constant.Constants;
import com.example.user_management.infrastructure.constant.Role;
import com.example.user_management.service.impl.UserTokenServiceImpl;
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

    @Autowired
    private UserTokenServiceImpl userTokenService;

    public String generateTokenUser(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 10000);
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, Constants.JWTSECRET)
                .compact();

        UserToken userTokenFindByIdUser = userTokenService.findUserTokenByUserId(user.getId());
        if (userTokenFindByIdUser == null) {
            UserToken userToken = new UserToken();
            userToken.setUserId(user.getId());
            userToken.setToken(token);
            userToken.setExpiredAt(expiryDate.getTime());
            userTokenService.save(userToken);
        } else {
            userTokenFindByIdUser.setToken(token);
            userTokenFindByIdUser.setExpiredAt(expiryDate.getTime());
            userTokenService.save(userTokenFindByIdUser);
        }
        return token;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Constants.JWTSECRET)
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
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(Constants.JWTSECRET)
                    .build()
                    .parseClaimsJws(token);

            Date expirationDate = claims.getBody().getExpiration();
            if (expirationDate.before(new Date())) {
                return false;
            }

            UserToken userToken = userTokenService.findUserTokenByToken(token);
            if (userToken == null) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
