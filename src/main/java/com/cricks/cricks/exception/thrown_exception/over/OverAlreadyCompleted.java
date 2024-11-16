package com.cricks.cricks.exception.thrown_exception.over;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class OverAlreadyCompleted  extends MainException{
  
  public OverAlreadyCompleted(String message , Integer statusCode){
    super(message , statusCode);
  } 
  
}
