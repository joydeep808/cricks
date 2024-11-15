package com.cricks.cricks.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cricks.cricks.exception.thrown_exception.MainException;
import com.cricks.cricks.util.Response;


@RestControllerAdvice
public class HandlerException {
  @ExceptionHandler(MainException.class)
  public ResponseEntity<Response<String>> sendError(MainException exception){
    return new Response<String>().sendErrorResponse(exception.getMessage() , exception.getStatusCode()).sendResponseEntity();
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Response<String>> sendError(Exception exception){
    return new Response<String>().sendErrorResponse(exception.getMessage(), 500).sendResponseEntity();
  }

}
