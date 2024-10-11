package com.example.Green_X.dto.ResponseDto;

import com.example.Green_X.enumtype.BusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllBusResponseDto {

    private int busId;

    private String busNumber;


    private String busName;


    private BusType busType;


    private int seatCapacity;


    private String imageFileName;
}
