package com.cricks.cricks.config;

import org.springframework.stereotype.Service;

import com.cricks.cricks.config.security.JwtService;
import com.cricks.cricks.dto.jwt.JwtUserInfoDto;
import com.cricks.cricks.exception.thrown_exception.cookies.CookieNotFound;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CookiesService{
  private final JwtService jwtService;
  private final Integer expirationTime = 60 * 60 * 24;
  
   public Boolean setAccessCookie(HttpServletResponse response ,JwtUserInfoDto jwtDto){
    Cookie accessCookie  = new Cookie("accessToken", jwtService.generateAccessToken(jwtDto));
    accessCookie.setMaxAge(expirationTime);
    accessCookie.setDomain("localhost");
    response.addCookie(accessCookie);
    return true;
  }


  public Boolean setRefreshCookie(HttpServletResponse response ,Integer id){
    Cookie accessCookie  = new Cookie("refreshToken", jwtService.generateRefreshToken(id));
    accessCookie.setMaxAge(expirationTime * 60 * 24);
    accessCookie.setDomain("localhost");
    response.addCookie(accessCookie);
    return true;
  }


  public JwtUserInfoDto getUserDetailsFromJwt(HttpServletRequest request , HttpServletResponse response) throws Exception{
   
    Cookie[] cookies = request.getCookies();
    // i want to check the accessToken first and then the refreshToken
    // if the accessToken is not found then i will check the refreshToken
    // i want to check for the db for user info 
    // if the user is not found then i will throw an exception
    // if the user is found then i will return the user info
    if(cookies != null){
      for (Cookie cookie : cookies) {
        if(cookie.getName().equals("accessToken")){
          return jwtService.getTokenInfo(cookie.getValue());
        }
      }
      for (Cookie cookie : cookies) {
        if(cookie.getName().equals("refreshToken")){
           Integer userId = jwtService.getUserId(cookie.getValue());
           JwtUserInfoDto jwtUserInfoDto = new JwtUserInfoDto();
           jwtUserInfoDto.setId(userId.toString());
           jwtUserInfoDto.setNumber("1234567890");
           jwtUserInfoDto.setRole("ADMIN");
           jwtService.generateAccessToken(jwtUserInfoDto); 
           setAccessCookie(response, jwtUserInfoDto);
           response.addCookie(cookie);
           return jwtUserInfoDto;
        }

      }
    }
    throw new CookieNotFound("Unauthorized Access", 401);
}
}
