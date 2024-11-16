package com.cricks.cricks.exception.thrown_exception.batsman;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class BatsmanStatsNotFound extends MainException{
  
  public BatsmanStatsNotFound(String message , Integer statusCode){
    super(message, statusCode);
  }
  
}
