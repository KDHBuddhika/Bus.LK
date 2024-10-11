package com.example.Green_X.controller;


import com.example.Green_X.dto.RegisterDto.BusRegisterDto;
import com.example.Green_X.dto.ResponseDto.AllBusResponseDto;
import com.example.Green_X.dto.UpdateDto.BusUpdateDto;
import com.example.Green_X.service.BusService;
import com.example.Green_X.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;

//    bus register
    @PostMapping({"/Register-bus"})
    private ResponseEntity<StandardResponse> RegisterBus(@ModelAttribute BusRegisterDto busRegisterDto){

        boolean status = busService.registerNewBus(busRegisterDto);
         return new ResponseEntity<StandardResponse>(
                 new StandardResponse(200,"Successfully",status), HttpStatus.CREATED
         );
    }

//------------------------------get all bus----------------------------

    @GetMapping({"/getAll-bus"})
    private ResponseEntity<StandardResponse> GetAllBus(){

        List<AllBusResponseDto> allBusResponseDtos = busService.getAllBus();

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully", allBusResponseDtos),HttpStatus.OK
        );
    }

//------------------------------get all private bus------------------------------
    @GetMapping({"/getAll-private-bus"})
    private ResponseEntity<StandardResponse> GetAllPrivateBus(){
        List<AllBusResponseDto> allBusResponseDtos = busService.getAllPrivateBus();

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully", allBusResponseDtos),HttpStatus.OK);
    }

// -----------------------------Search bus by Bus Number And BusType = Private---------------------

    @GetMapping(path = "/get-privateBus-by-busNumber", params = "busNumber")
    private ResponseEntity<StandardResponse> GetPrivateBusByBusNumber(@RequestParam(value = "busNumber") String busNumber ){
        AllBusResponseDto busResponseDto = busService.getPrivateBusByBusNumber(busNumber);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully", busResponseDto),HttpStatus.OK);

    }

//---------------Search bus by Bus name and Bus Type = PRIVATE-------------------------
@GetMapping(path = "/get-privateBus-by-busName", params = "busName")
private ResponseEntity<StandardResponse> GetPrivateBusByBusName(@RequestParam(value = "busName") String busName ){
    List<AllBusResponseDto> busResponseDto = busService.getPrivateBusByBusName(busName);

    return new ResponseEntity<StandardResponse>(
            new StandardResponse(200, "Successfully", busResponseDto),HttpStatus.OK);

}


//----------------------Delete PRIVATE BUS by BusID----------------------------------------
    @DeleteMapping(path = "/delete-parivateBus-by-busId",
                    params = "busId"
    )
    private ResponseEntity<StandardResponse> DeletePrivateBusByBusId(@RequestParam(value = "busId") int busId){

        boolean status = busService.deletePrivateBusByBusId(busId);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Delete Successfully", status),HttpStatus.OK);
    }


    //----------------------Update PRIVATE BUS by BusID----------------------------------------

    @PutMapping(
            path = "/update-PrivateBus-by-busId/{busId}"
    )
    private ResponseEntity<StandardResponse> UpdatePrivateBusById(@PathVariable(value = "busId") int busId,  @ModelAttribute BusUpdateDto busUpdateDto){
        boolean status = busService.updatePrivateBusById(busId,busUpdateDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Update Successfully", status),HttpStatus.OK);

    }




    //------------------------------get all CTB bus------------------------------
    @GetMapping({"/getAll-ctb-bus"})
    private ResponseEntity<StandardResponse> GetAllCtbBus(){
        List<AllBusResponseDto> allBusResponseDtos = busService.getAllCtbBus();

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully", allBusResponseDtos),HttpStatus.OK);
    }



    // -----------------------------Search bus by Bus Number And BusType = CTB---------------------

    @GetMapping(path = "/get-ctbBus-by-busNumber", params = "busNumber")
    private ResponseEntity<StandardResponse> GetCtbBusByBusNumber(@RequestParam(value = "busNumber") String busNumber ){
        AllBusResponseDto busResponseDto = busService.getCtbBusByBusNumber(busNumber);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully", busResponseDto),HttpStatus.OK);

    }

    //----------------------Delete CTB BUS by BusID----------------------------------------
    @DeleteMapping(path = "/delete-ctbBus-by-busId",
            params = "busId"
    )
    private ResponseEntity<StandardResponse> DeleteCtbBusByBusId(@RequestParam(value = "busId") int busId){

        boolean status = busService.deleteCtbBusByBusId(busId);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Delete Successfully", status),HttpStatus.OK);
    }



    //----------------------Update CTB BUS by BusID----------------------------------------

    @PutMapping(
            path = "/update-ctbBus-by-busId",
            params = "busId"
    )
    private ResponseEntity<StandardResponse> UpdateCtbBusById(
            @RequestParam(value = "busId") int busId,  @ModelAttribute BusUpdateDto busUpdateDto){
        boolean status = busService.updateCtbBusById(busId,busUpdateDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Update Successfully", status),HttpStatus.OK);

    }







}
