package com.fast.miniproject.auth.jwt;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String makeJwtToken(User user) { //토큰 발급 시기를 생각해보면 토큰은 로그인하고나서 (findByEmailAndPassword) 실행후이므로 실행 결과값은 Optional<entity> 나옴
        // 그것을 넣어야 하기 때문에 entity 인 Member객체가 매개변수로 들어간다.
        // 토큰을 문자열이므로 반환값은 String 이 된다.
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer()) // 누가 발급했나.?
                .setIssuedAt(now) //토큰 발급시간
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // 토큰 발급 시간 기준 얼마나 유지 시킬건지.
                .claim("email", user.getEmail()) // 페이로드에 현재 엔티티의 정보
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public LoginReqDTO tokenToUser(String token) {

        if (validationToken(token)) {//토큰이 Bearer로 시작하는지 형식이 맞는지 확인

            Claims claims = null;
            token = extractToken(token); // header에서 토큰 추출 (Bearer뗌)
            claims = tokenToClaims(token);//
            return new LoginReqDTO(claims);
        }
        return null;

    }
    public Claims tokenToClaims(String token) {
        //토큰을 받아와서 Claims 로 바꿔주는 녀석
        //jwt필터에서 시큐리티 필터로 넘어가기전에 토큰을 시큐리티 필터가 알수 있게 바꿔준다고 생각하면됨.
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

    }

    private boolean validationToken(String token) { //토큰값이 유효한지 체크

        if (token == null || !(token.startsWith(jwtProperties.getTokenPrefix()))) {
            return false;
        }
        return true;
    }

    private String extractToken(String authorizationHeader) { //토큰 (Bearer) 떼고 토큰값만 가져오는 메서드
        return authorizationHeader.substring(jwtProperties.getTokenPrefix().length());
    }
}
