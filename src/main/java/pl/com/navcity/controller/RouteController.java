package pl.com.navcity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
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
import pl.com.navcity.service.CarServiceImpl;
import pl.com.navcity.service.DriverServiceImpl;
import pl.com.navcity.service.RouteServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    CarServiceImpl carService;

    @Autowired
    DriverServiceImpl driverService;

    @Autowired
    RouteServiceImpl routeService;

    @GetMapping(path ="/list")
    public String showMenu(Model model){
        model.addAttribute("listOfRoutes", routeService.getAllRoutes());
        return "mainPanel";
    }

    @GetMapping(path="/route-form")
    public String prepareAddNewRouteForm(@RequestParam(value = "routeId", required = false) Integer routeId, Model model){

        model.addAttribute("listOfCars", carService.getAllCars());
        model.addAttribute("listOfDrivers", driverService.getAllDrivers());

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

    @PostMapping(path="/add-route")
    public String addNewRoute(@Valid Route newRoute,
                              BindingResult bindingResult,
                              @RequestParam(value = "routeId", required = false) Integer routeId,
                              @RequestParam("carId") Integer carId,
                              @RequestParam("driverId") Integer driverId,
                              Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("listOfCars", carService.getAllCars());
            model.addAttribute("listOfDrivers", driverService.getAllDrivers());
            return "addRoute";
        }

        System.out.println(" route id " + routeId);

        if(routeId == null){

            Car car = carService.getCarById(carId);
            car.setRouteDurationAndDistance(newRoute);
            Driver driver = driverService.getDriverById(driverId);
            driver.setRouteDurationAndDistance(newRoute);
            routeService.saveRoute(newRoute);

        }else{
            Route oldRoute = routeService.getRouteById(routeId);
            Car newCar = carService.getCarById(carId);
            newCar.updateRouteDurationAndDistance(newCar, newRoute, oldRoute);
            Driver newDriver = driverService.getDriverById(driverId);
            newDriver.updateRouteDurationAndDistance(newDriver, newRoute, oldRoute);
            routeService.updateRoute(newRoute, oldRoute);
        }
        return "redirect:/api/routes/list";
    }


    @GetMapping(path="/delete-route")
    public String deleteCarFromDatabase(@RequestParam("routeId") Integer routeId){

       routeService.deleteRouteById(routeId);
       return "redirect:/api/routes/list";
    }
}
