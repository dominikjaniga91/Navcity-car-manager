package pl.com.navcity.model.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.navcity.model.Route;
import pl.com.navcity.repository.DriverRepositoryImpl;
import pl.com.navcity.repository.RouteRepositoryImpl;

import java.util.List;


public enum RouteReports {

    ROUTE_DISTANCE("Show all routes with distance greater than 100 km"){
        public List<Route> myReport(){
            return ReportClass.getAllRoutesWithDistanceGreater();
        }
    },

    ROUTE_DURATION("Show all routes with duration higher than 1 h"){
        public List<Route> myReport(){
            return ReportClass.getAllRoutesWithDurationGreater();
        }
    };


    @Component
    public static class ReportClass{

        static RouteRepositoryImpl routeRepository;

        @Autowired
        public ReportClass(RouteRepositoryImpl routeRepository) {
            this.routeRepository = routeRepository;
        }

        public static List<Route> getAllRoutesWithDistanceGreater(){

            return  routeRepository.getAllByDistanceGreaterThanEqual(100.00);
        }

        public static List<Route> getAllRoutesWithDurationGreater(){

            return  routeRepository.getAllByDurationGreaterThanEqual(1.0);
        }



    }

    String value;

    RouteReports(String value) {
        this.value = value;
    }


    public String returnValue() {
        return  value;
    }

    public abstract List<Route> myReport();
}
