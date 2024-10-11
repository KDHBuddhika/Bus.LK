package com.example.Green_X.entity;

import com.example.Green_X.enumtype.BusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bus")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int busId;

    @Column (name = "bus_number",unique = true,nullable = false)
    private String busNumber;

    @Column(name = "bus_name")
    private String busName;

    @Column(name = "bus_type" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private BusType busType;

    @Column(name = "bus_capacity")
    private int seatCapacity;

    @Column(name = "Image_file_name")
    private String imageFileName;

}
