package com.cricks.cricks.exception.thrown_exception.over;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class OverAlreadyExist extends MainException{
  public  OverAlreadyExist(String message , Integer statusCode){
    super(message, statusCode);

  }
  
}
