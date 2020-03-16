package pl.com.navcity.repository;

import pl.com.navcity.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DriverRepositoryImpl extends JpaRepository<Driver, Integer> {

    Driver getDriverById(Integer id);

    List<Driver> getAllByDistanceGreaterThanEqual(Double distance);

    List<Driver> getAllByDurationGreaterThanEqual(Double duration);
}
