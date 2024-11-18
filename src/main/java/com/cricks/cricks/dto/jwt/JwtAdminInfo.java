package com.cricks.cricks.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)  
@AllArgsConstructor
@NoArgsConstructor
public class JwtAdminInfo extends JwtUserInfoDto{
  private String teamId;
}
