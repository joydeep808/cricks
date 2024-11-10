package com.cricks.cricks.util;


import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

  private String message;
  private Integer statusCode;
  private T data;

  public   Response sendSuccessResponse(String message , Integer statusCode , T data){
    this.data = data;
    this.message = message;
    this.statusCode = statusCode;
    return this;
  }

  public   Response sendSuccessResponse(String message , Integer statusCode){
    this.message = message;
    this.statusCode = statusCode;
    return this;
  }

  public   Response sendErrorResponse(String message , Integer statusCode){
    this.message = message;
    this.statusCode = statusCode;
    return this;
  }
  
  public ResponseEntity<Response<T>> sendResponseEntity(){
    return  ResponseEntity.status(this.statusCode).body(this);
  }

  
}
