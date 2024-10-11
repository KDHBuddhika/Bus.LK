package com.example.Green_X.repo;

import com.example.Green_X.entity.Bus;
import com.example.Green_X.enumtype.BusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BusRepo extends JpaRepository<Bus,Integer> {
    Bus findByBusNumber(String busNumber);


    List<Bus> findByBusTypeEquals(BusType busType);

    Bus findByBusNumberEqualsAndBusTypeEquals(String busNumber, BusType busType);

    List<Bus> findByBusNameEqualsAndBusTypeEquals(String busName, BusType busType);
}
