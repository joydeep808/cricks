package com.cricks.cricks.entity;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;
    
    private Integer teamA;
    private Integer teamB;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private LocalDateTime  matchDate;
    @NotNull(message = "Venue id is required")
    private Integer venueId;
    private MatchStatus status;
    @Column(nullable = false)
    @NotNull(message = "Series id is required")
    private Integer seriesId;
    private Integer winningTeam;
    @Column(nullable = false )
    @NotNull(message = "Match number should be required")
    private Integer matchNumber;
    private MatchType match_type;
    public enum MatchType{
        ODI,
        T20,
        TEST
    }


   
    public enum MatchStatus {
        PENDING,
        INPROGRESS,
        COMPLETED, 
        SCHEDULED
    }
    
    public Match(){
        this.status= MatchStatus.PENDING;
    }
}
