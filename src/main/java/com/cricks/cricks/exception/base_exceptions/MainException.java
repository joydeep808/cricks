
package com.cricks.cricks.exception.base_exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainException extends  Exception{
  private String message;
  private Integer statusCode;
  
  public MainException(String message , Integer statusCode){
    super(message);
    this.message =message;
    this.statusCode = statusCode;
  }

}
