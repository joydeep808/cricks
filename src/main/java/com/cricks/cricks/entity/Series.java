package com.cricks.cricks.entity;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Series {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer id;
    @NotNull(message="Name is required")
   private String name;
   @JsonFormat(shape=Shape.STRING)
   @NotNull(message="Start Date is required")
   private LocalDateTime start_date;
   @JsonFormat(shape=Shape.STRING)
   @NotNull(message="End date is required")
   private LocalDateTime end_date;
   private String description;
   @NotNull(message="Match type should be required")
   private MatchType match_type;
   enum MatchType{
    ODI,
    T20,
    TEST
}
}
