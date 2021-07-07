package com.codegym.service.jwt;

import com.codegym.model.UserPrincipal;
import org.slf4j.Logger;
import io.jsonwebtoken.*;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.util.Date;

// Sinh ra đoạn token
// Mã hóa header, pay load và signature
@Service
public class JwtService {
    // Mã bí mật, đặt là gì cũng được
    private static final String SECRET_KEY = "123456789";
    // Thời gian hết hạn
    private static final long EXPIRE_TIME = 86400000000L;
    // Ghi log
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());

    // Sinh ra token khi login thành công
    public String generateTokenLogin(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // thực thi tất cả đoạn trên và sinh ra đoạn token
                .compact();
    }

    // Kiểm tra token hệ thống sinh ra có hợp lệ không
    public boolean validateJwtToken(String authToken) throws SignatureException {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    // Giải mã ngược đoạn token truyền đi để lấy ra userName;
    public String getUserNameFromJwtToken(String token) {
        String userName = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userName;
    }
}