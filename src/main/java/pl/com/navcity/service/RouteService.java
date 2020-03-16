package pl.com.navcity.service;

import pl.com.navcity.model.Route;

import java.util.List;

public interface RouteService {

    Route getRouteById(Integer routeId);

    List<Route> getAllRoutes();

    void deleteRouteById(Integer routeId);

    void updateRoute(Route newRoute, Route oldRoute);

    void saveRoute(Route route);

}
