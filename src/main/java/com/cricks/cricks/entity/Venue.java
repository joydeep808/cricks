package com.cricks.cricks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Venue {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;
    @NotNull(message="Country is required")
    @NotNull(message = "Venue name is required")
    private String venueName;
    private String country;
    @NotNull(message="Location is required")

    private String location;
    @NotNull(message="City is required")
    
    private String city;
}
