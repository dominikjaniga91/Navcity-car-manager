package pl.com.navcity.repository;


import pl.com.navcity.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepositoryImpl extends JpaRepository<Route, Integer> {

    Route getRouteById(Integer id);

    List<Route> getAllByDistanceGreaterThanEqual(Double distance);

    List<Route> getAllByDurationGreaterThanEqual(Double duration);
}
