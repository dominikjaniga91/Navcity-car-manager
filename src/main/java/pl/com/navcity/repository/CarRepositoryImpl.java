package pl.com.navcity.repository;

import pl.com.navcity.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepositoryImpl extends JpaRepository<Car, Integer> {

    Car getCarById(Integer id);

    List<Car> getCarsByProductionYearGreaterThan(int year);

    List<Car> getCarsByProductionYearLessThan(int year);
}
