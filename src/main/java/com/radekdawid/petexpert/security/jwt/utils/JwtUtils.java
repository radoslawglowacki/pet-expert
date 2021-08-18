package com.radekdawid.petexpert.security.jwt.utils;


import com.radekdawid.petexpert.users.role.model.Role;
import com.radekdawid.petexpert.users.user.model.User;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@NoArgsConstructor
public class JwtUtils {


    private final String jwtSecret = "secret";
    private final int jwtExpirationMs = 900000;


    public String generateJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setHeaderParam("id", user.getId())
                .setHeaderParam("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public Long getUserIdFromJwtToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(token);

        return ((Number) claimsJws.getHeader().get("id")).longValue();
    }

    public String getEmailFromJwtToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    public List<Role> getUserRolesFromJwtToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(token);

//        TODO

        return null;
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
