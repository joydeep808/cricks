package com.cricks.cricks.config.security;

import java.util.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.cricks.cricks.dto.jwt.JwtDto;

import io.jsonwebtoken.*;
import jakarta.servlet.http.*;

@Service
public class JwtService {

  private final Integer expirationTime = 60 * 60 * 24;
  private final SecretKey secret;

  public JwtService() {
    String secret = "fhklshdfhsldhflkshdflh";
    byte[] secretBytes = secret.getBytes();
    this.secret = new SecretKeySpec(secretBytes, "HmacSHA256");
  }

  public String generateAccessToken(JwtDto createJwt) {
    HashMap<String, Object> claims = new HashMap<>();
    claims.put("id", createJwt.getId());
    claims.put("number", createJwt.getNumber());
    claims.put("role", createJwt.getRole());
    
    return Jwts.builder().claims(claims).issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expirationTime)).signWith(secret).compact();

  }

  public String generateRefreshToken(Integer id) {
     return Jwts.builder().claim("id" , id).issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expirationTime * 60  * 24)).signWith(secret).compact();
  }

  public Boolean setAccessCookie(HttpServletResponse response ,JwtDto jwtDto){
    Cookie accessCookie  = new Cookie("accessToken", generateAccessToken(jwtDto));
    accessCookie.setMaxAge(expirationTime);
    accessCookie.setDomain("localhost");
    response.addCookie(accessCookie);
    return true;
  }


  public Boolean setRefreshCookie(HttpServletResponse response ,Integer id){
    Cookie accessCookie  = new Cookie("refreshToken", generateRefreshToken(id));
    accessCookie.setMaxAge(expirationTime * 60 * 24);
    accessCookie.setDomain("localhost");
    response.addCookie(accessCookie);
    return true;
  }


  @SuppressWarnings("deprecation")
  public Claims extractClaims(String token)throws Exception {
   return  Jwts.parser().verifyWith(secret).build().parseClaimsJws(token).getPayload();
  }

  public JwtDto getTokenInfo(String token) throws Exception{
    Claims claims = extractClaims(token);
    JwtDto jwtDto = new JwtDto();
    jwtDto.setId((String) claims.get("id"));
    jwtDto.setNumber((String) claims.get("number"));
    jwtDto.setRole((String) claims.get("role"));
    return jwtDto;
  }
  public Integer getUserId(String token) throws Exception{
    return (Integer) extractClaims(token).get("id");
  }




}
