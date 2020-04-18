package pl.com.navcity.service;


import pl.com.navcity.model.Car;
import pl.com.navcity.model.Driver;
import pl.com.navcity.model.Route;
import pl.com.navcity.repository.RouteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepositoryImpl routeDao;
    private CarServiceImpl carService;
    private DriverServiceImpl driverService;

    @Autowired
    public RouteServiceImpl(RouteRepositoryImpl routeDao, CarServiceImpl carService, DriverServiceImpl driverService) {
        this.routeDao = routeDao;
        this.carService = carService;
        this.driverService = driverService;
    }

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
        Driver driver = route.getDriver();
        driver.deleteRouteAndUpdateTimeDistance(route);
        routeDao.deleteById(routeId);
    }

    @Override
    public void updateRoute(Route newRoute, Integer routeId, Integer carId, Integer driverId) {

        Route oldRoute = routeDao.getRouteById(routeId);
        Car newCar = carService.getCarById(carId);
        newCar.updateRouteDurationAndDistance(newCar, newRoute, oldRoute);
        Driver newDriver = driverService.getDriverById(driverId);
        newDriver.updateRouteDurationAndDistance(newDriver, newRoute, oldRoute);

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
    public void saveRoute(Route route, Integer carId, Integer driverId) {

        Car car = carService.getCarById(carId);
        car.setRouteDurationAndDistance(route);
        Driver driver = driverService.getDriverById(driverId);
        driver.setRouteDurationAndDistance(route);

        routeDao.save(route);
    }
}
