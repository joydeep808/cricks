package com.cricks.cricks.exception.thrown_exception.team;


import com.cricks.cricks.exception.thrown_exception.MainException;

public class TeamNotFound extends MainException{
  public TeamNotFound(String message , Integer statusCode){
    super(message, statusCode);

  }
  
}
