package com.example.Green_X.service;

import com.example.Green_X.dto.RegisterDto.BusRegisterDto;
import com.example.Green_X.dto.ResponseDto.AllBusResponseDto;
import com.example.Green_X.dto.UpdateDto.BusUpdateDto;

import java.util.List;

public interface BusService {

    boolean registerNewBus(BusRegisterDto busRegisterDto);

    List<AllBusResponseDto> getAllBus();

    List<AllBusResponseDto> getAllPrivateBus();

    AllBusResponseDto getPrivateBusByBusNumber(String busNumber);

    List<AllBusResponseDto> getPrivateBusByBusName(String busName);

    boolean deletePrivateBusByBusId(int busId);

    boolean updatePrivateBusById(int busId,BusUpdateDto busUpdateDto);

    AllBusResponseDto getCtbBusByBusNumber(String busNumber);

    List<AllBusResponseDto> getAllCtbBus();

    boolean deleteCtbBusByBusId(int busId);

    boolean updateCtbBusById(int busId, BusUpdateDto busUpdateDto);
}
