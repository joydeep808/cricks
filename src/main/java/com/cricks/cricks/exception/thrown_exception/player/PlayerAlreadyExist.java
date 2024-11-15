package com.cricks.cricks.exception.thrown_exception.player;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class PlayerAlreadyExist extends MainException {
  public  PlayerAlreadyExist(String message , Integer statusCode){
    super(message , statusCode);
  }
}
