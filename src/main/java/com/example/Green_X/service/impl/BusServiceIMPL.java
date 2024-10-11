package com.example.Green_X.service.impl;

import com.example.Green_X.dto.RegisterDto.BusRegisterDto;
import com.example.Green_X.dto.ResponseDto.AllBusResponseDto;
import com.example.Green_X.dto.UpdateDto.BusUpdateDto;
import com.example.Green_X.entity.Bus;
import com.example.Green_X.enumtype.BusType;
import com.example.Green_X.exception.NotFoundException;
import com.example.Green_X.exception.UserAlreadyExist;
import com.example.Green_X.repo.BusRepo;
import com.example.Green_X.service.BusService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BusServiceIMPL implements BusService {

    @Autowired
    private BusRepo busRepo;


    @Override
    public boolean registerNewBus(BusRegisterDto busRegisterDto) {

        Bus ExistBus = busRepo.findByBusNumber(busRegisterDto.getBusNumber());

        if(ExistBus != null){
             throw new UserAlreadyExist("Bus is Already Exist");


        }else {

            //save image file
            MultipartFile image =busRegisterDto.getImageFileName();
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

            try{
                String uploadDir ="D:/Project/Spring-boot project/Green_X/Green-X/Green-X/public/busImages/";
                Path uploadPath= Paths.get(uploadDir);

                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
                try(InputStream inputStream =image.getInputStream()){
                    Files.copy(inputStream , Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            Bus bus = new Bus();
            bus.setBusName(busRegisterDto.getBusName());
            bus.setBusNumber(busRegisterDto.getBusNumber());
            bus.setBusType(busRegisterDto.getBusType());
            bus.setSeatCapacity(busRegisterDto.getSeatCapacity());
            bus.setImageFileName(storageFileName);


            busRepo.save(bus);
            return true;

        }

    }


    // -------------------------Get All Bus------------------------------------------
    @Override
    public List<AllBusResponseDto> getAllBus() {
        List<Bus> buses = busRepo.findAll();
        if(buses.size() > 0){
            List<AllBusResponseDto> busResponses = new ArrayList<>();

            for (Bus bus:buses) {
                AllBusResponseDto busResponseDto = new AllBusResponseDto(
                        bus.getBusId(),
                        bus.getBusNumber(),
                        bus.getBusName(),
                        bus.getBusType(),
                        bus.getSeatCapacity(),
                        bus.getImageFileName()
                );

                busResponses.add(busResponseDto);

            }
            return busResponses;
        }else{

            throw new NotFoundException("Not Available Buses ");
        }


    }


    // -------------------------Get All Private Bus------------------------------------------

    @Override
    public List<AllBusResponseDto> getAllPrivateBus() {
        BusType busType = BusType.PRIVATE;
        List<Bus> buses = busRepo.findByBusTypeEquals(busType);
        if(buses.size() > 0){
            List<AllBusResponseDto> busResponses = new ArrayList<>();

            for (Bus bus:buses) {
                AllBusResponseDto busResponseDto = new AllBusResponseDto(
                        bus.getBusId(),
                        bus.getBusNumber(),
                        bus.getBusName(),
                        bus.getBusType(),
                        bus.getSeatCapacity(),
                        bus.getImageFileName()
                );

                busResponses.add(busResponseDto);

            }
            return busResponses;
        }else{

            throw new NotFoundException("Any Private is Not Available  ");
        }

    }



    //-----------------------------Search bus by Bus Number And BusType = Private---------------------

    @Override
    public AllBusResponseDto getPrivateBusByBusNumber(String busNumber) {
        BusType busType= BusType.PRIVATE;
        Bus bus = busRepo.findByBusNumberEqualsAndBusTypeEquals(busNumber,busType);

        if(bus != null){
            AllBusResponseDto busResponseDto = new AllBusResponseDto();
            busResponseDto.setBusId(bus.getBusId());
            busResponseDto.setBusNumber(bus.getBusNumber());
            busResponseDto.setBusName(bus.getBusName());
            busResponseDto.setBusType(bus.getBusType());
            busResponseDto.setSeatCapacity(bus.getSeatCapacity());
            busResponseDto.setImageFileName(bus.getImageFileName());

            return busResponseDto;
        }
        else
        {
            throw new NotFoundException("Bus with number " + busNumber + " not found.");
        }
    }




    //---------------------Search bus by Bus name and Bus Type = PRIVATE-------------------------
    @Override
    public List<AllBusResponseDto> getPrivateBusByBusName(String busName) {
        BusType busType= BusType.PRIVATE;
       List<Bus> buses = busRepo.findByBusNameEqualsAndBusTypeEquals(busName,busType);

        if(buses.size()>0){
            List<AllBusResponseDto> busResponses = new ArrayList<>();

            for (Bus bus:buses) {
                AllBusResponseDto busResponseDto = new AllBusResponseDto(
                        bus.getBusId(),
                        bus.getBusNumber(),
                        bus.getBusName(),
                        bus.getBusType(),
                        bus.getSeatCapacity(),
                        bus.getImageFileName()
                );

                busResponses.add(busResponseDto);

            }
            return busResponses;
        }else{

            throw new NotFoundException("Any Private is Not Available for "+busName);
        }
    }



//----------------------Delete PRIVATE BUS by BusID----------------------------------------

    @Override
    public boolean deletePrivateBusByBusId(int busId) {
     if(busRepo.existsById(busId)){
         Bus bus = busRepo.findById(busId).get();
         Path imagePath  = Paths.get("D:/Project/Spring-boot project/Green_X/Green-X/Green-X/public/busImages/"+bus.getImageFileName());
         try {
             Files.delete(imagePath);
         }catch (Exception ex){
             throw new NotFoundException(ex.getMessage());
         }
         busRepo.deleteById(busId);
         return true;
     }else{
         throw  new NotFoundException("Bus is Not Found");
     }

    }



    //----------------------Update PRIVATE BUS by BusID----------------------------------------

    @Override
    public boolean updatePrivateBusById(int busId,BusUpdateDto busUpdateDto) {
        if(busRepo.existsById(busId)){
              Bus bus = busRepo.findById(busId).get();
              Bus updateBus = new Bus();
              if(busUpdateDto.getImageFileName() != null && !busUpdateDto.getImageFileName().isEmpty()){
                  //delete existing image
                  Path imagePath = Paths.get("D:/Project/Spring-boot project/Green_X/Green-X/Green-X/public/busImages/"+bus.getImageFileName());
                  try{
                      Files.delete(imagePath);

                  }catch (Exception ex)
                  {
                      throw new RuntimeException(ex.getMessage());
                  }

                  //save image file
                  MultipartFile image =busUpdateDto.getImageFileName();
                  Date createdAt = new Date();
                  String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                  try{
                      String uploadDir ="D:/Project/Spring-boot project/Green_X/Green-X/Green-X/public/busImages/";
                      Path uploadPath= Paths.get(uploadDir);

                      if(!Files.exists(uploadPath)){
                          Files.createDirectories(uploadPath);
                      }
                      try(InputStream inputStream =image.getInputStream()){
                          Files.copy(inputStream , Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                      }

                  }catch (Exception e){
                      System.out.println(e.getMessage());
                  }

                bus.setBusName(busUpdateDto.getBusName());
                bus.setBusType(busUpdateDto.getBusType());
                bus.setBusNumber(busUpdateDto.getBusNumber());
                bus.setSeatCapacity(busUpdateDto.getSeatCapacity());
                bus.setImageFileName(storageFileName);


              }else{
                  bus.setBusName(busUpdateDto.getBusName());
                  bus.setBusType(busUpdateDto.getBusType());
                  bus.setBusNumber(busUpdateDto.getBusNumber());
                  bus.setSeatCapacity(busUpdateDto.getSeatCapacity());
              }
            busRepo.save(bus);
            return true;

        }
        else{
             throw new NotFoundException("Not Founded");
        }
    }




    // -------------------------Get All CTB Bus------------------------------------------

    @Override
    public List<AllBusResponseDto> getAllCtbBus() {
        BusType busType = BusType.CTB;
        List<Bus> buses = busRepo.findByBusTypeEquals(busType);
        if(buses.size() > 0){
            List<AllBusResponseDto> ctbBusResponses = new ArrayList<>();

            for (Bus bus:buses) {
                AllBusResponseDto busResponseDto = new AllBusResponseDto(
                        bus.getBusId(),
                        bus.getBusNumber(),
                        bus.getBusName(),
                        bus.getBusType(),
                        bus.getSeatCapacity(),
                        bus.getImageFileName()
                );

                ctbBusResponses.add(busResponseDto);

            }
            return ctbBusResponses;
        }else{

            throw new NotFoundException("Any CTB is Not Available  ");
        }

    }




    //-----------------------------Search bus by Bus Number And BusType = CTB---------------------

    @Override
    public AllBusResponseDto getCtbBusByBusNumber(String busNumber) {
        BusType busType= BusType.CTB;
        Bus bus = busRepo.findByBusNumberEqualsAndBusTypeEquals(busNumber,busType);

        if(bus != null){
            AllBusResponseDto busResponseDto = new AllBusResponseDto();
            busResponseDto.setBusId(bus.getBusId());
            busResponseDto.setBusNumber(bus.getBusNumber());
            busResponseDto.setBusName(bus.getBusName());
            busResponseDto.setBusType(bus.getBusType());
            busResponseDto.setSeatCapacity(bus.getSeatCapacity());
            busResponseDto.setImageFileName(bus.getImageFileName());

            return busResponseDto;
        }
        else
        {
            throw new NotFoundException("Bus with number " + busNumber + " not found.");
        }
    }



    //----------------------Delete Ctb BUS by BusID----------------------------------------

    @Override
    public boolean deleteCtbBusByBusId(int busId) {
        if(busRepo.existsById(busId)){
            Bus bus = busRepo.findById(busId).get();
            Path imagePath  = Paths.get("D:/Project/Spring-boot project/Green_X/Green-X/Green-X/public/busImages/"+bus.getImageFileName());
            try {
                Files.delete(imagePath);
            }catch (Exception ex){
                throw new NotFoundException(ex.getMessage());
            }
            busRepo.deleteById(busId);
            return true;
        }else{
            throw  new NotFoundException("Bus is Not Found");
        }

    }

    //----------------------Update CTB BUS by BusID----------------------------------------

    @Override
    public boolean updateCtbBusById(int busId,BusUpdateDto busUpdateDto) {
        if(busRepo.existsById(busId)){
            Bus bus = busRepo.findById(busId).get();
            Bus updateBus = new Bus();
            if(busUpdateDto.getImageFileName() != null && !busUpdateDto.getImageFileName().isEmpty()){
                //delete existing image
                Path imagePath = Paths.get("D:/Project/Spring-boot project/Green_X/Green-X/Green-X/public/busImages/"+bus.getImageFileName());
                try{
                    Files.delete(imagePath);

                }catch (Exception ex)
                {
                    throw new NotFoundException(ex.getMessage());
                }

                //save image file
                MultipartFile image =busUpdateDto.getImageFileName();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try{
                    String uploadDir ="D:/Project/Spring-boot project/Green_X/Green-X/Green-X/public/busImages/";
                    Path uploadPath= Paths.get(uploadDir);

                    if(!Files.exists(uploadPath)){
                        Files.createDirectories(uploadPath);
                    }
                    try(InputStream inputStream =image.getInputStream()){
                        Files.copy(inputStream , Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                    }

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                bus.setBusName(busUpdateDto.getBusName());
                bus.setBusType(busUpdateDto.getBusType());
                bus.setBusNumber(busUpdateDto.getBusNumber());
                bus.setSeatCapacity(busUpdateDto.getSeatCapacity());
                bus.setImageFileName(storageFileName);


            }else{
                bus.setBusName(busUpdateDto.getBusName());
                bus.setBusType(busUpdateDto.getBusType());
                bus.setBusNumber(busUpdateDto.getBusNumber());
                bus.setSeatCapacity(busUpdateDto.getSeatCapacity());
            }
            busRepo.save(bus);
            return true;

        }
        else{
            throw new NotFoundException("Not Founded");
        }
    }


}
