
package com.cricks.cricks.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Umpire {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer Id;
  private String name;
  private Integer umpire_profile;
  
}
