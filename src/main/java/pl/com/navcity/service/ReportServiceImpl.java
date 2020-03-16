package pl.com.navcity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.navcity.model.Car;
import pl.com.navcity.repository.CarRepositoryImpl;
import pl.com.navcity.repository.DriverRepositoryImpl;
import pl.com.navcity.repository.RouteRepositoryImpl;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReportServiceImpl {


    @Autowired
    private CarRepositoryImpl carRepository;

    @Autowired
    private RouteRepositoryImpl routeRepository;

    @Autowired
    private DriverRepositoryImpl driverRepository;


    public List<Car> listOfCarOlderThan(int years){

        int year = LocalDate.now().getYear()-years;

        System.out.println(Arrays.toString(carRepository.findAll().toArray()));
        return carRepository.findAll();
    }

}
