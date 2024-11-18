package com.cricks.cricks.annotation.annotationprocessor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cricks.cricks.annotation.rawannotation.UpdateAuthenticationAnnotation;
import com.cricks.cricks.config.CookiesService;
import com.cricks.cricks.dto.jwt.JwtAdminInfo;
import com.cricks.cricks.exception.thrown_exception.cookies.UnauthorizedAccess;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@Aspect
@RequiredArgsConstructor
public class UpdateAnnotationProcessor {

  private final CookiesService  cookiesService;
  @Before(value = "annotation(com.cricks.cricks.annotation.rawannotation.UpdateAuthenticationAnnotation)")
  public void checkAuthentication(UpdateAuthenticationAnnotation annotation) throws Exception {
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
   HttpServletRequest request = attributes.getRequest(); // Get the HttpServletRequest
   HttpServletResponse response = attributes.getResponse(); // Get the HttpServletResponse

   String teamId = annotation.teamId();
   if (!teamId.isEmpty()) {
      JwtAdminInfo adminInfo = cookiesService.getUserDetailsFromJwt(request, response);
      if (adminInfo.getTeamId() != teamId) {
        throw new UnauthorizedAccess("You are not authorized to update this team", 401);
      }
   }
   throw new UnauthorizedAccess("You are not authorized to update", 401);
    
  }
}
