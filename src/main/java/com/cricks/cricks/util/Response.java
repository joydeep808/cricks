package com.cricks.cricks.util;


import org.hibernate.sql.ast.tree.predicate.BooleanExpressionPredicate;
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
  private Boolean status;

  public   Response<T> sendSuccessResponse(String message , Integer statusCode , T data){
    this.data = data;
    this.message = message;
    this.statusCode = statusCode;
    this.status = true;
    return this;
  }

  public   Response<T> sendSuccessResponse(String message , Integer statusCode){
    this.message = message;
    this.statusCode = statusCode;
    this.status = true;
    return this;
  }

  public   Response<T> sendErrorResponse(String message , Integer statusCode){
    this.message = message;
    this.statusCode = statusCode;
    this.status = false;
    return this;
  }
  
  public ResponseEntity<Response<T>> sendResponseEntity(){
    return  ResponseEntity.status(this.statusCode).body(this);
  }

  
}
