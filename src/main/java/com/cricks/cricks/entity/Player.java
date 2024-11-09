package com.cricks.cricks.entity.player;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable=false)
    @NotNull(message="Please enter the player name")
    private String name;

    private String profile_url;
    @NotNull(message="Team id is required")
    private Integer team_id;
    private Integer ranking;

    private PlayerRoles role;
    private LocalDateTime createdAt;
    




    enum PlayerRoles {
        KEEPER,
        BATSMAN,
        BOWLER,
        ALLROUNDER
    }

   
}



