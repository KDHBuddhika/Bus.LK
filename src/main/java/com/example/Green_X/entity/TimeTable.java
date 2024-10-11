package com.example.Green_X.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timetableId;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Routes route;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

}
