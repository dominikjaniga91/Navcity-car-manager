package pl.com.navcity.service;

import pl.com.navcity.model.Car;
import java.util.List;

public interface CarService {

    void createSomeData();

    List<Car> getAllCars();

    Car getCarById(Integer carId);

    void saveCar(Car car);

    void updateCar(Integer carId, Car car);

    void deleteCarById(Integer carId);

}
