package com.cricks.cricks.exception.thrown_exception.player;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class PlayerNotFound extends MainException{
  public PlayerNotFound(String message , Integer statusCode){
    super(message, statusCode);
  }
}
