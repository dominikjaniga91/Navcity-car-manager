package pl.com.navcity.model.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.navcity.model.Driver;
import pl.com.navcity.model.Route;
import pl.com.navcity.repository.CarRepositoryImpl;
import pl.com.navcity.repository.DriverRepositoryImpl;

import java.util.List;


public enum DriverReports {

    DRIVER_DISTANCE("Show all drivers with distance greater than 100 km"){
        public List<Driver> myReport(){
            return ReportClass.getAllDriversWithDistanceGreater();
        }
    },

    DRIVER_DURATION("Show all drivers with duration higher than 1 h"){
        public List<Driver> myReport(){
            return ReportClass.getAllDriversWithDurationGreater();
        }
    };

    @Component
    public static class ReportClass{

        static DriverRepositoryImpl driverRepository;

        @Autowired
        public ReportClass(DriverRepositoryImpl driverRepository) {
            this.driverRepository = driverRepository;
        }

        public static List<Driver> getAllDriversWithDistanceGreater(){

            return  driverRepository.getAllByDistanceGreaterThanEqual(100.00);
        }

        public static List<Driver> getAllDriversWithDurationGreater(){

            return  driverRepository.getAllByDurationGreaterThanEqual(1.0);
        }

    }

    String value;

    DriverReports(String value) {
        this.value = value;
    }


    public String returnValue() {
        return  value;
    }

    public abstract List<Driver> myReport();
}
