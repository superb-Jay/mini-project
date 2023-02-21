package com.fast.miniproject.auth.jwt;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.dto.TokenDTO;
import com.fast.miniproject.auth.entity.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public TokenDTO makeJwtToken(User user) { //토큰 발급 시기를 생각해보면 토큰은 로그인하고나서 (findByEmailAndPassword) 실행후이므로 실행 결과값은 Optional<entity> 나옴
        // 그것을 넣어야 하기 때문에 entity 인 Member객체가 매개변수로 들어간다.
        // 토큰을 문자열이므로 반환값은 String 이 된다.
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer()) // 누가 발급했나.?
                .setIssuedAt(now) //토큰 발급시간
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // 토큰 발급 시간 기준 얼마나 유지 시킬건지.
                .claim("email", user.getEmail()) // 페이로드에 현재 엔티티의 정보
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer()) // 누가 발급했나.?
                .setIssuedAt(now) //토큰 발급시간
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(1440).toMillis())) // 토큰 발급 시간 기준 얼마나 유지 시킬건지.
                .claim("email", user.getEmail()) // 페이로드에 현재 엔티티의 정보
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

        return TokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }


    public LoginReqDTO tokenToUser(String accessToken) {

        try {
            accessToken = extractToken(accessToken);
            Claims claims = null;
            claims = tokenToClaims(accessToken);//
            return new LoginReqDTO(claims);
        } catch (Exception e) {
            return null;
        }
    }

    public Claims tokenToClaims(String accessToken) {
        //토큰을 받아와서 Claims 로 바꿔주는 녀석
        //jwt필터에서 시큐리티 필터로 넘어가기전에 토큰을 시큐리티 필터가 알수 있게 바꿔준다고 생각하면됨.
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(accessToken)
                .getBody();

    }

    private boolean validationAccessToken(String accessToken) { //토큰값이 유효한지 체크

        if (accessToken == null || !(accessToken.startsWith(jwtProperties.getTokenPrefix()))) {
            return false;
        }
        return true;
    }

    private String extractToken(String authorizationHeader) { //토큰 (Bearer) 떼고 토큰값만 가져오는 메서드
        return authorizationHeader.substring(jwtProperties.getTokenPrefix().length());
    }


    public String recreationAccessToken(String userEmail) {

        Date now = new Date();
        //Access Token
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer()) // 누가 발급했나.?
                .setIssuedAt(now) //토큰 발급시간
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // 토큰 발급 시간 기준 얼마나 유지 시킬건지.
                .claim("email", userEmail) // 페이로드에 현재 엔티티의 정보
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }


}
