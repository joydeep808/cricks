package com.cricks.cricks.dto.over;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOverDto {
  public Integer overNumber;
  public Integer matchNumber;
  public Integer teamId;
}
