package com.cricks.cricks.config.security;

import java.util.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.cricks.cricks.dto.jwt.JwtUserInfoDto;

import io.jsonwebtoken.*;

@Service
public class JwtService {

  private final Integer expirationTime = 60 * 60 * 24;
  private final SecretKey secret;

  public JwtService() {
    String secret = "fhklshdfhsldhflkshdflh";
    byte[] secretBytes = secret.getBytes();
    this.secret = new SecretKeySpec(secretBytes, "HmacSHA256");
  }

  public String generateAccessToken(JwtUserInfoDto createJwt) {
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

 


  @SuppressWarnings("deprecation")
  public Claims extractClaims(String token)throws Exception {
   return  Jwts.parser().verifyWith(secret).build().parseClaimsJws(token).getPayload();
  }

  public JwtUserInfoDto getTokenInfo(String token) throws Exception{
    Claims claims = extractClaims(token);
    JwtUserInfoDto jwtUserInfoDto = new JwtUserInfoDto();
    jwtUserInfoDto.setId((String) claims.get("id"));
    jwtUserInfoDto.setNumber((String) claims.get("number"));
    jwtUserInfoDto.setRole((String) claims.get("role"));
    return jwtUserInfoDto;
  }
  public Integer getUserId(String token) throws Exception{
    return (Integer) extractClaims(token).get("id");
  }




}
