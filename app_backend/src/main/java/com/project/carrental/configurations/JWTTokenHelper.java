package com.project.carrental.configurations;

import com.project.carrental.models.User;
import com.project.carrental.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Component
public class JWTTokenHelper {

    @Value("{$jwt.auth.app}")
    private String appName;

    @Value("{$jwt.auth.secret_key}")
    private String secretKey;

    @Value("{$jwt.auth.expires_in}")
    private int expiresIn;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    public Date generateExpirationDate() {
        return new Date(new Date().getTime() + expiresIn * 1000);
    }

    public String generateToken(String username) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return Jwts.builder()
                .setIssuer(appName)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();
    }

    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);

        return (username != null && username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = generateExpirationDate();

        return expirationDate.before(new Date());
    }

    public Date getExpirationDate(String token) {
        Date expirationDate;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expirationDate = claims.getExpiration();
        } catch (Exception e) {
            expirationDate = null;
        }

        return expirationDate;
    }

    public Date getIssuedAtDate(String token) {
        Date issuedDate;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issuedDate = claims.getIssuedAt();
        } catch (Exception e) {
            issuedDate = null;
        }

        return issuedDate;
    }

    public String getToken(HttpServletRequest request) {
        String authorizationHeader = getAuthorizationHeader(request);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

    public String getAuthorizationHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
