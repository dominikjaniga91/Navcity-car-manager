package pl.com.navcity.service;

import pl.com.navcity.model.Driver;

import java.util.List;

public interface DriverService {

    void createSomeDrivers();

    List<Driver> getAllDrivers();

    Driver getDriverById(Integer driverId);

    void saveDriver(Driver driver);

    void updateDriver(Integer driverId, Driver driver);

    void deleteDriverById(Integer driverId);
}
