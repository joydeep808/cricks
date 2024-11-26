package com.cricks.cricks.jwt;

import org.springframework.stereotype.Service;

import com.cricks.cricks.dto.jwt.JwtAdminInfo;
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
  
   public Boolean setAccessCookie(HttpServletResponse response ,JwtAdminInfo jwtDto){
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


  public JwtAdminInfo getUserDetailsFromJwt(HttpServletRequest request , HttpServletResponse response) throws Exception{
   
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
           JwtAdminInfo JwtAdminInfo = new JwtAdminInfo();
           JwtAdminInfo.setId(userId.toString());
           JwtAdminInfo.setNumber("1234567890");
           JwtAdminInfo.setRole("ADMIN");
           jwtService.generateAccessToken(JwtAdminInfo); 
           setAccessCookie(response, JwtAdminInfo);
           response.addCookie(cookie);
           return JwtAdminInfo;
        }

      }
    }
    throw new CookieNotFound("Unauthorized Access", 401);
}
}
