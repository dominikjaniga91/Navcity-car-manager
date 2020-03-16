package pl.com.navcity.controller;


import pl.com.navcity.exception.DriverNotFoundException;
import pl.com.navcity.model.Driver;
import pl.com.navcity.repository.DriverRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DriverRestController {

    @Autowired
    private DriverRepositoryImpl driverDao;

    @GetMapping(path = "/drivers")
    public ResponseEntity<List<Driver>> findAll(){
        return ResponseEntity.ok(driverDao.findAll());
    }

    @GetMapping(path = "/drivers/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Integer id){

        Driver driver = driverDao.findById(id).orElseThrow(
                ()->new DriverNotFoundException(id));
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/drivers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDriver(@PathVariable Integer id) {
        Driver driver = driverDao.findById(id).orElseThrow(
                ()->new DriverNotFoundException(id));
        driverDao.delete(driver);
    }

    @PostMapping("/addTheDriver")
    @ResponseStatus(HttpStatus.OK)
    public void addDriver(@RequestBody Driver driver) {
        driverDao.save(driver);
    }

    @PutMapping("/updateTheDriver")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateDriver(@RequestBody Driver driver) {
        driverDao.save(driver);
    }



}
