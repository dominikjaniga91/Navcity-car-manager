package pl.com.navcity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name="driver")
public class Driver {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotEmpty(message = "{empty.correctlength}")
    @NotBlank(message = "{blank.correctlength}")
    @Column(name="first_name")
    private String firtsName;

    @NotEmpty(message = "{empty.correctlength}")
    @NotBlank(message = "{blank.correctlength}")
    @Column(name="last_name")
    private String lastName;

    @NotEmpty(message = "{empty.correctlength}")
    @NotBlank(message = "{blank.correctlength}")
    @Column(name="licence")
    private String licence;

    @Column(name="distance")
    private double distance = 0.0;

    @Column(name="duration")
    private double duration = 0.0;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="driver_id")
    private List<Route> routeList = new ArrayList<>();


    public Driver(String firtsName, String lastName, String licence) {
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.licence = licence;
    }

    public Driver() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    /* -------------------------  distance and duration -------------------------------*/

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance += distance;
    }

    public void updateDistance(double newDistance, double oldDistance){
        double difference = newDistance - oldDistance;
        this.duration += difference;
    }

    public void decrementDistance(double distance) {
        this.distance -= distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration += duration;
    }

    public void updateDuration(double newDuration, double oldDuration){
        double difference = newDuration - oldDuration;
        this.distance += difference;
    }

    public void decrementDuration(double duration) {
        this.duration -= duration;
    }

    public LocalTime getLocalTimeDuration(){

        int hours = (int) this.duration;
        int minutes = (int)((this.duration - hours) * 60);
        int seconds = (int)(this.duration * 3600) % 3600 - minutes * 60;
        return LocalTime.of(hours, minutes, seconds);
    }

    public void setRouteDurationAndDistance(Route route){
        if(!routeList.contains(route)){
            routeList.add(route);
        }
        setDistance(route.getDistance());
        setDuration(route.getDuration());
    }

    public void updateRouteDurationAndDistance(Driver newDriver, Route newRoute, Route oldRoute){

        if(!newDriver.getId().equals(oldRoute.getCar().getId())){

            oldRoute.getCar().deleteRouteAndUpdateTimeDistance(oldRoute);
            newDriver.setRouteDurationAndDistance(newRoute);
        } else{

            updateDistance(newRoute.getDistance(), oldRoute.getDistance());
            updateDuration(newRoute.getDuration(), oldRoute.getDuration());

        }
    }

    public void deleteRouteAndUpdateTimeDistance(Route route, LocalDateTime startDate){

            routeList.remove(route);
            decrementDistance(route.getDistance());
            decrementDuration(route.getDuration());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id.equals(driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return  String.format( "%d %s %s %s"  ,id, firtsName,  lastName,  licence);
    }

    public String returnShortDetails() {

        return  String.format( "%s %s "  ,firtsName,  lastName);
    }
}
