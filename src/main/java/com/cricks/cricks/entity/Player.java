package com.cricks.cricks.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Builder
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false)
    @NotNull(message = "Please enter the player name")
    private String name;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Please provide the phone number")
    private Integer phone;
    private String profileUrl;
    @NotNull(message = "Team id is required")
    private Integer teamId;
    private Integer ranking;
    private LocalDate dateOfBirth;
    private PlayerStatus status;
    private PlayerRoles role;
    private LocalDateTime createdAt;

    public enum PlayerStatus{
        CAPPED,
        UNCAPPED,
        RETIRED
    }
    public enum PlayerRoles {
        KEEPER,
        BATSMAN,
        BOWLER,
        ALLROUNDER
    }

    public Player() {
        this.createdAt = LocalDateTime.now();
        this.status = PlayerStatus.UNCAPPED;
    }

}
