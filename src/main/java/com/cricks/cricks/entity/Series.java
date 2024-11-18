package com.cricks.cricks.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@Data
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotNull(message = "Name is required")
    private String name;
    @JsonFormat(shape = Shape.STRING)
    @NotNull(message = "Start Date is required")
    private LocalDateTime start_date;
    @JsonFormat(shape = Shape.STRING)
    @NotNull(message = "End date is required")
    private LocalDateTime end_date;
    private String description;

    private SeriesStatus status;

    public enum SeriesStatus {
        END,
        ONGOING,
        PENDING
    }

    public Series() {
        this.status = SeriesStatus.PENDING;
    }

}
