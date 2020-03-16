package pl.com.navcity.model.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.navcity.model.Car;
import pl.com.navcity.repository.CarRepositoryImpl;

import java.util.List;


public enum CarReports {

    CAR_OLDER_THAN_REPORT("Cars with production year higher than 5 years"){

        public List<Car> myReport(){
            return ReportClass.getAllCarsByProductionYearLower();

        }

    },

    CAR_YOUNGER_THAN_REPORT("Cars with production year lower than 5 years"){

        public List<Car> myReport(){
            return ReportClass.getAllCarsByProductionYearGreater();

        }

    };
    String value;

    @Component
    public static class ReportClass{

        static CarRepositoryImpl carRepository;

        @Autowired
        public ReportClass(CarRepositoryImpl carRepository) {
            this.carRepository = carRepository;
        }

        public static List<Car> getAllCarsByProductionYearGreater(){

            return  carRepository.getCarsByProductionYearGreaterThan(2015);
        }

        public static List<Car> getAllCarsByProductionYearLower(){

            return  carRepository.getCarsByProductionYearLessThan(2015);
        }


    }

    CarReports(String value) {
        this.value = value;
    }

    public String returnValue() {
        return  value;
    }

    public abstract List<Car> myReport();
}
