package com.cricks.cricks.entity.match;

import java.time.*;

import com.cricks.cricks.entity.match.Match.MatchStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
public class Match {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer team_a;
    private Integer team_b;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private LocalDateTime  match_date;
    private Integer venue_id;
    private MatchStatus status;
    private Integer series_id;
    


   
    enum MatchStatus {
        PENDING,
        INPROGRESS,
        COMPLETED
    }
    
    public Match(){
        this.status= MatchStatus.PENDING;
    }
}
