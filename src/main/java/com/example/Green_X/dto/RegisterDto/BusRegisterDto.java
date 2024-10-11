package com.example.Green_X.dto.RegisterDto;

import com.example.Green_X.enumtype.BusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusRegisterDto {

    @ApiModelProperty(value = "Bus number", required = true)
    private String busNumber;

    @ApiModelProperty(value = "Bus name", required = true)
    private String busName;

    @ApiModelProperty(value = "Bus type", required = true)
    private BusType busType;

    @ApiModelProperty(value = "Seat capacity", required = true)
    private int seatCapacity;

    @ApiModelProperty(value = "Bus image file")
    private MultipartFile imageFileName;


}
