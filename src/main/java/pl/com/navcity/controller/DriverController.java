package pl.com.navcity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.navcity.model.Driver;
import pl.com.navcity.model.Route;
import pl.com.navcity.service.DriverServiceImpl;
import pl.com.navcity.service.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/drivers")
public class DriverController {

    private DriverServiceImpl driverService;
    private RouteServiceImpl routeService;

    @Autowired
    public DriverController(DriverServiceImpl driverService, RouteServiceImpl routeService) {
        this.driverService = driverService;
        this.routeService = routeService;
    }

    @GetMapping("/list")
    public String showCarList(Model model){

        model.addAttribute("listOfDrivers", driverService.getAllDrivers());
        return "drivers";
    }

    @GetMapping(path="/driver-form")
    public String prepareAddDriverForm(@RequestParam(value = "driverId", required = false) Integer driverId, Model model){

        if(driverId != null){
            Driver driver = driverService.getDriverById(driverId);
            model.addAttribute("listOfRoutes", driver.getRouteList());
            model.addAttribute("driver", driver);
            return "updateDriver";
        } else{
            model.addAttribute("driver", new Driver());
            return "addDriver";
        }

    }

    @PostMapping("/add-driver")
    public String createOrUpdateCar(@Valid Driver driver, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            return "addCar";
        }
        driverService.saveDriver(driver);
        return "redirect:/api/drivers/list";
    }

    @PostMapping("/update-driver")
    public String  updateCar(@Valid Driver driver,
                             BindingResult bindingResult,
                             @RequestParam("driverId") Integer driverId){

        if(bindingResult.hasErrors()){
            return "updateDriver";
        }

        driverService.updateDriver(driverId, driver);
        return "redirect:/api/drivers/list";
    }

    @GetMapping(path="/delete-driver")
    public String deleteCarFromDatabase(@RequestParam("driverId") Integer driverId, Model model){
        Driver driver = driverService.getDriverById(driverId);

        if(!driver.getRouteList().isEmpty()){
            model.addAttribute("listOfDrivers", driverService.getAllDrivers());
            model.addAttribute("deleteDriverError", "cannot delete driver");
            return "drivers";
        }
        driverService.deleteDriverById(driverId);

        return "redirect:/api/drivers/list";
    }

}
