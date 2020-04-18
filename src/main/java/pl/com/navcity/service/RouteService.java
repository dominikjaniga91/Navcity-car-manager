package pl.com.navcity.service;

import pl.com.navcity.model.Route;

import java.util.List;

public interface RouteService {

    Route getRouteById(Integer routeId);

    List<Route> getAllRoutes();

    void deleteRouteById(Integer routeId);

    void updateRoute(Route newRoute, Integer routeId, Integer carId, Integer driverId);

    void saveRoute(Route route, Integer carId, Integer driverId);

}
