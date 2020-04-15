package pl.com.navcity.controller;

import pl.com.navcity.model.Car;
import pl.com.navcity.model.Color;
import pl.com.navcity.model.Driver;
import pl.com.navcity.model.Route;
import pl.com.navcity.repository.CarRepositoryImpl;
import pl.com.navcity.repository.DriverRepositoryImpl;
import pl.com.navcity.repository.RouteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.navcity.service.RouteServiceImpl;

import javax.validation.Valid;

@Controller
public class RouteController {

    @Autowired
    CarRepositoryImpl carDao;

    @Autowired
    DriverRepositoryImpl driverDao;

    @Autowired
    RouteServiceImpl routeService;

    @GetMapping(path ="/routes")
    public String showMenu(Model model){
        model.addAttribute("listOfRoutes", routeService.getAllRoutes());
        return "mainPanel";
    }

    @GetMapping(path="/addRouteForm")
    public String prepareAddNewRouteForm(@RequestParam(value = "routeId", required = false) Integer routeId, Model model){

        model.addAttribute("listOfCars", carDao.findAll());
        model.addAttribute("listOfDrivers", driverDao.findAll());

        if(routeId != null){
            model.addAttribute("route", routeService.getRouteById(routeId));
            model.addAttribute("myCarId", routeService.getRouteById(routeId)
                                                         .getCar()
                                                         .getId());
            model.addAttribute("myDriverId", routeService.getRouteById(routeId)
                                                            .getDriver()
                                                            .getId());

        } else{
            model.addAttribute("route", new Route());
        }
        return "addRoute";
    }

    @PostMapping(path="/addRoute")
    public String addNewRoute(@Valid Route newRoute,
                              BindingResult bindingResult,
                              @RequestParam(value = "routeId", required = false) Integer routeId,
                              @RequestParam("carId") Integer carId,
                              @RequestParam("driverId") Integer driverId,
                              Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("listOfCars", carDao.findAll());
            model.addAttribute("listOfDrivers", driverDao.findAll());
            return "addRoute";
        }

        System.out.println(" route id " + routeId);

        if(routeId == null){

            Car car = carDao.getCarById(carId);
            car.setRouteDurationAndDistance(newRoute);
            Driver driver = driverDao.getDriverById(driverId);
            driver.setRouteDurationAndDistance(newRoute);
            routeService.saveRoute(newRoute);

        }else{
            Route oldRoute = routeService.getRouteById(routeId);
            Car newCar = carDao.getCarById(carId);
            newCar.updateRouteDurationAndDistance(newCar, newRoute, oldRoute);
            Driver newDriver = driverDao.getDriverById(driverId);
            newDriver.updateRouteDurationAndDistance(newDriver, newRoute, oldRoute);
            routeService.updateRoute(newRoute, oldRoute);
        }
        return "redirect:/";
    }


    @GetMapping(path="/deleteRoute")
    public String deleteCarFromDatabase(@RequestParam("routeId") Integer routeId){

       routeService.deleteRouteById(routeId);
       return "redirect:/";
    }
}
