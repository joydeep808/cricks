package com.cricks.cricks.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfoDto {
  public String id;
  public String number;
  public String role;
}
