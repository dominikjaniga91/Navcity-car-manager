package pl.com.navcity.model;

import pl.com.navcity.model.validation.TravelDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@TravelDateTime(message = "{validation.date}")
@Entity(name="route")
public class Route {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="route_name")
    @NotEmpty(message = "{validation.empty}")
    @NotBlank(message = "{validation.blank}")
    private String routeName;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="departure_date")
    @NotNull(message = "{validation.empty}")
    @Future(message = "{validation.date.future}")
    private LocalDateTime departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="arrival_date")
    @NotNull(message = "{validation.empty}")
    private LocalDateTime arrivalDate;

    @Column(name="departure_address", length = 100)
    @NotEmpty(message = "{validation.empty}")
    @NotBlank(message = "{validation.blank}")
    private String departureAddress;

    @Column(name="destination_address", length = 100)
    @NotEmpty(message = "{validation.empty}")
    @NotBlank(message = "{validation.blank}")
    private String destinationAddress;

    @Column(name="distance")
    @NotNull(message = "{validation.empty}")
    private double distance;

    @Column(name="duration")
    @NotNull(message = "{validation.empty}")
    private double duration;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Driver driver;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Car car;

    public Route(String routeName, LocalDateTime departureDate, LocalDateTime arrivalDate, String departureAddress, String destinationAddress) {
        this.routeName = routeName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
    }

    public Route(String routeName, LocalDateTime departureDate, LocalDateTime arrivalDate, String departureAddress, String destinationAddress, double distance, double duration) {
        this.routeName = routeName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
        this.distance = distance;
        this.duration = duration;
    }



    public Route() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(String departureAddress) {
        this.departureAddress = departureAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    //Method use to set initial distance
    public void setDistance(Double distance) {

        this.distance = distance/1000;
    }

    public double getDistance() {
        return distance;
    }

    //Method use to set initial duration
    public void setDuration(Double duration) {

        this.duration = duration / 3600;
    }

    public double getDuration() {

      return this.duration;

    }

    public void updateDistance(Double distance){
        this.distance = distance;
    }

    public void updateDuration(Double duration){
        this.duration = duration;
    }

    public LocalTime getLocalTimeDuration(){

        int hours = (int) this.duration;
        int minutes = (int)((this.duration - hours) * 60);
        int seconds = (int)(this.duration * 3600) % 3600 - minutes * 60;

        System.out.println( " hours " + hours + " minutes " + minutes + " seconds " + seconds );
        return LocalTime.of(hours, minutes, seconds);
    }

    public Driver getDriver() {
        return driver;
    }

    public Car getCar() {
        return car;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isIdEqualsNull(){
        System.out.println(" isIdEqualsNull ");
        System.out.println(this.id == null);
        System.out.println(" id " + id);
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id.equals(route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return String.format("%d %s %s %s",id, routeName, departureAddress, destinationAddress );
    }
}

