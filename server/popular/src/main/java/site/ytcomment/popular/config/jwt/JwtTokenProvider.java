package site.ytcomment.popular.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;
    private final long tokenValidityInSeconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.time}") long tokenValidityInSeconds){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.tokenValidityInSeconds = tokenValidityInSeconds * 1000;
    }

    // 토큰생성
    public String createToken(String email, String userId){
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInSeconds);

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            log.error("유효하지 않은 JWT 토큰 {}", e.getMessage());
            return false;
        }
    }

    // 토큰에서 이메일 추출
    public String getEmailFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 토큰에서 userId 추출
    public String getUserIdFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", String.class);
    }

    // 인증 정보 생성
    public Authentication getAuthentication(String token){
        String email = getEmailFromToken(token);
        return new UsernamePasswordAuthenticationToken(email, null
                , List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
