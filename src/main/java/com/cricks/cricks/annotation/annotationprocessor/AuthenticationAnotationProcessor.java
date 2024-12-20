package com.cricks.cricks.annotation.annotationprocessor;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;

import com.cricks.cricks.annotation.rawannotation.*;
import com.cricks.cricks.annotation.rawannotation.AuthenticationAnnotation.Role;
import com.cricks.cricks.dto.jwt.JwtUserInfoDto;
import com.cricks.cricks.exception.thrown_exception.cookies.UnauthorizedAccess;
import com.cricks.cricks.jwt.CookiesService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@Aspect
@RequiredArgsConstructor
public class AuthenticationAnotationProcessor {
  private final CookiesService cookiesService;
  @Before(value = "annotation(com.cricks.cricks.config.security.AuthenticationAnotation")
  public void checkAuthentication(AuthenticationAnnotation annotation ) throws Exception {
  // Accessing HttpServletRequest and HttpServletResponse from RequestContextHolder
   ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
   HttpServletRequest request = attributes.getRequest(); // Get the HttpServletRequest
   HttpServletResponse response = attributes.getResponse(); // Get the HttpServletResponse

    Role role = annotation.role();
    if (role == Role.ADMIN) {
     JwtUserInfoDto userDetailsFromJwt = cookiesService.getUserDetailsFromJwt(request, response);;
     if(!userDetailsFromJwt.getRole().equals("ADMIN")){
      throw new UnauthorizedAccess("You are not an Admin" , 401);
     }
    }
    else {
      JwtUserInfoDto userDetailsFromJwt = cookiesService.getUserDetailsFromJwt(request, response);
      if(!userDetailsFromJwt.getRole().equals("USER")){
        throw new UnauthorizedAccess("You are not a User" , 401);
      }
    }

  }
}
