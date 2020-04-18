package pl.com.navcity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.navcity.model.Car;
import pl.com.navcity.model.Color;
import pl.com.navcity.service.CarServiceImpl;
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
@RequestMapping("/api/cars")
public class CarController {


    private CarServiceImpl carService;
    private RouteServiceImpl routeService;

    @Autowired
    public CarController(CarServiceImpl carService, RouteServiceImpl routeService) {
        this.carService = carService;
        this.routeService = routeService;
    }

    @GetMapping("/list")
    public String showCarList(Model model){

        model.addAttribute("listOfCars", carService.getAllCars());
        return "cars";
    }

   @GetMapping("/car-form")
   public String prepareAddCarForm(@RequestParam(value = "carId", required = false) Integer carId, Model model){

       model.addAttribute("colors", Color.values());
        if(carId != null){
            Car car = carService.getCarById(carId);
            model.addAttribute("car", car);
            model.addAttribute("listOfRoutes", car.getRouteList());
            return "updateCar";
        } else{
            model.addAttribute("car", new Car());
            return "addCar";
        }
    }

   @PostMapping("/add-car")
    public String createCar(@Valid Car car, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
           return "addCar";
       }

       carService.saveCar(car);

       return "redirect:/api/cars/list";
   }

   @PostMapping("/update-car")
   public String  updateCar(@Valid Car car,
                            BindingResult bindingResult,
                            @RequestParam("carId") Integer carId,
                            Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("colors", Color.values());
            return "updateCar";
        }

       carService.updateCar(carId, car);

       return "redirect:/api/cars/list";
   }

   @GetMapping(path="/delete-car")
    public String deleteCarFromDatabase(@RequestParam("carId") Integer carId, Model model){

        Car car = carService.getCarById(carId);
        if(!car.getRouteList().isEmpty()){
            model.addAttribute("listOfCars", carService.getAllCars());
            model.addAttribute("deleteCarError", "cannot delete car");

            return "cars";
        }

        carService.deleteCarById(carId);
        return "redirect:/api/cars/list";
   }

}
