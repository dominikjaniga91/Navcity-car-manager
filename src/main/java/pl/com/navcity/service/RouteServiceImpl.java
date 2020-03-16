package pl.com.navcity.service;


import pl.com.navcity.model.Car;
import pl.com.navcity.model.Route;
import pl.com.navcity.repository.RouteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {


    @Autowired
    RouteRepositoryImpl routeDao;

    @Override
    public Route getRouteById(Integer routeId){

        return routeDao.getRouteById(routeId);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeDao.findAll();
    }

    @Override
    public void deleteRouteById(Integer routeId) {
        Route route = routeDao.getRouteById(routeId);
        Car car = route.getCar();
        car.deleteRouteAndUpdateTimeDistance(route);
        routeDao.deleteById(routeId);
    }

    @Override
    public void updateRoute(Route newRoute, Route oldRoute) {

        oldRoute.setRouteName(newRoute.getRouteName());
        oldRoute.setDepartureDate(newRoute.getDepartureDate());
        oldRoute.setArrivalDate(newRoute.getArrivalDate());
        oldRoute.setDepartureAddress(newRoute.getDepartureAddress());
        oldRoute.setDestinationAddress(newRoute.getDestinationAddress());
        oldRoute.updateDistance(newRoute.getDistance());
        oldRoute.updateDuration(newRoute.getDuration());

        routeDao.save(oldRoute);

    }

    @Override
    public void saveRoute(Route route) {
        routeDao.save(route);
    }
}
