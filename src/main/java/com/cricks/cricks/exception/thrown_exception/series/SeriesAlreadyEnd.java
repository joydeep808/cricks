package com.cricks.cricks.exception.thrown_exception.series;

import com.cricks.cricks.exception.thrown_exception.MainException;

public class SeriesAlreadyEnd extends MainException {
  public SeriesAlreadyEnd(String message , Integer statusCode){
    super(message , statusCode);
  }
  
}
