package pl.com.navcity.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{validation.empty}")
    @NotBlank(message = "{validation.blank}")
    @Column(name="brand")
    private String brand;

    @NotEmpty(message = "{validation.empty}")
    @NotBlank(message = "{validation.blank}")
    @Column(name="model")
    private String model;

    @NotEmpty(message = "{validation.empty}")
    @NotBlank(message = "{validation.blank}")
    @Column(name="vin_number", length = 60)
    private String vinNumber;

    @NotNull(message = "{validation.empty}")
    @Column(name="color", length = 30)
    @Enumerated(EnumType.STRING)
    private Color color;

    @NotNull(message = "{validation.empty}")
    @Max(value = 2020, message = "{validation.productionYear}")
    @Column(name="production_year")
    private int productionYear;

    @Size(max=225, message = "{notes.correctlength}")
    @Column(name="notes", length = 225)
    private String notes;

    @Column(name="distance")
    private double distance = 0.0;

    @Column(name="duration")
    private double duration = 0.0;

    @OneToMany
    @JoinColumn(name="car_id")
    private List<Route> routeList = new ArrayList<>();

    public Car(Integer id, String brand, String model, String vinNumber, Color color, int productionYear, String notes) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.vinNumber = vinNumber;
        this.color = color;
        this.productionYear = productionYear;
        this.notes = notes;

    }

    public Car(String brand, String model, String vinNumber, Color color, int productionYear, String notes) {
        this.brand = brand;
        this.model = model;
        this.vinNumber = vinNumber;
        this.color = color;
        this.productionYear = productionYear;
        this.notes = notes;

    }

    public Car() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public void setDistance(double distance) {
        this.distance += distance;
    }

    public void updateDistance(double newDistance, double oldDistance){
        this.distance += newDistance - oldDistance;
    }

    public void updateDuration(double newDuration, double oldDuration){
        this.duration += newDuration - oldDuration;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration += duration;
    }

    public void decrementDistance(double distance) {
        this.distance -= distance;
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
        routeList.add(route);
        setDistance(route.getDistance());
        setDuration(route.getDuration());
    }

    public void updateRouteDurationAndDistance(Car newCar, Route newRoute, Route oldRoute){

        if(!newCar.getId().equals(oldRoute.getCar().getId())){

            oldRoute.getCar().deleteRouteAndUpdateTimeDistance(oldRoute);
            newCar.setRouteDurationAndDistance(newRoute);
        } else{

            updateDistance(newRoute.getDistance(), oldRoute.getDistance());
            updateDuration(newRoute.getDuration(), oldRoute.getDuration());

        }
    }

    public void deleteRouteAndUpdateTimeDistance(Route route){

            routeList.remove(route);
            decrementDistance(route.getDistance());
            decrementDuration(route.getDuration());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  String.format( "%d %s %s %s %s %d "  , id, brand,  model,  vinNumber,  color, productionYear);
    }

    public String returnShortDetails() {
        return String.format( "%s %s "  ,brand,  model);
    }
}