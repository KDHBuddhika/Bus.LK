package com.example.Green_X.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Routes {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int routeId;

      private String routeNumber;

      @ManyToOne
      @JoinColumn(name = "bus_id",nullable = false)
      private Bus bus;

      @ManyToOne
      @JoinColumn(name = "start_location_id", nullable = false)
      private Location location1;

      @ManyToOne
      @JoinColumn(name = "end_location_id" , nullable = false)
      private Location location2;



}
