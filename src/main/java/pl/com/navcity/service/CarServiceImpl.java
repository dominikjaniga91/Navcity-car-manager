package pl.com.navcity.service;

import pl.com.navcity.model.Car;
import pl.com.navcity.model.Color;
import pl.com.navcity.model.Route;
import pl.com.navcity.repository.CarRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepositoryImpl carDao;

    @Override
    public List<Car> getAllCars(){
        return carDao.findAll();
    }

    @Override
    public Car getCarById(Integer carId){

        return carDao.getCarById(carId);
    }

    @Override
    public void saveCar(Car car) {
        carDao.save(car);
    }

    @Override
    public void updateCar(Integer carId, Car car) {

        Car foundedCar = carDao.getCarById(carId);
        foundedCar.setBrand(car.getBrand());
        foundedCar.setModel(car.getModel());
        foundedCar.setVinNumber(car.getVinNumber());
        foundedCar.setColor(car.getColor());
        foundedCar.setProductionYear(car.getProductionYear());
        foundedCar.setNotes(car.getNotes());

        carDao.save(foundedCar);

    }

    @Override
    public void deleteCarById(Integer carId) {
        carDao.deleteById(carId);
    }

    @Override
    @PostConstruct
    public void createSomeData() {
        System.out.println(" class " + getClass());
        carDao.save(new Car("Porshe", "Panamera", "5443123221234", Color.Black, 2011, null));
        carDao.save(new Car("Fiat", "Tipo", "527272646575", Color.Blue, 1999, null));
        carDao.save(new Car("Mazda", "MX5", "23441233122", Color.Orange, 2005, null));
        carDao.save(new Car("Opel", "Vectra", "976687542431", Color.Purple, 2010, null));
        carDao.save(new Car("Opel", "Astra II", "1231323435353", Color.Silver, 1999, null));
        carDao.save(new Car("Volkswagen", "Golf 4", "2164645764747", Color.White, 2006, null));
        carDao.save(new Car("Seat", "Ibiza", "123452646463", Color.Yellow, 2018, null));
        carDao.save(new Car("Dodge", "Challenger", "42163463463463", Color.Brown, 2020, "very fast car"));
        carDao.save(new Car("Renault", "Clio", "5234632463646", Color.Black, 2000, null));
        carDao.save(new Car("Audi", "A4", "6234661344353", Color.Blue, 2003, null));
        carDao.save(new Car("Honda", "CRV", "23463634513425", Color.White, 2008, null));
        carDao.save(new Car("Skoda", "Octavia", "23516858568673", Color.Green, 2001, null));

    }
}
