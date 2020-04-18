package pl.com.navcity.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.com.navcity.model.Car;
import pl.com.navcity.model.Color;
import pl.com.navcity.model.Route;
import pl.com.navcity.service.CarServiceImpl;
import pl.com.navcity.service.RouteServiceImpl;
import pl.com.navcity.service.UserDetailsServiceImpl;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CarController.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class CarControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarServiceImpl carService;

    @MockBean
    RouteServiceImpl routeService;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    Route oldRoute;
    Route newRoute;
    Car car;

    @BeforeEach
    void setUp(){
        car = new Car(1, "Porsche", "Panamera", "5443123221234", Color.Black, 2011, null);
        oldRoute =  new Route("Zakopianka",
                LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(10,0,0)),
                LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(12,0,0)),
                "Kraków", "Zakopane", 145.67, 123535);

        newRoute =  new Route("Zakopianka",
                LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(10,0,0)),
                LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(12,0,0)),
                "Kraków", "Zakopane", 105.67, 123456);

    }


    @Test
    @WithMockUser(username = "Dominik", roles = {"ADMIN", "MANAGER"})
    void resultShouldMatchCar_whenConnectToEndpoint() throws Exception {

        BDDMockito.given(carService.getAllCars()).willReturn(Arrays.asList(car));

        mockMvc.perform(get("/api/cars/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listOfCars", hasSize(1)))
                .andExpect(model().attribute("listOfCars", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("brand", is("Porsche")),
                                hasProperty("model", is("Panamera")),
                                hasProperty("vinNumber", is("5443123221234")),
                                hasProperty("color", is(Color.Black)),
                                hasProperty("productionYear", is(2011)),
                                hasProperty("notes", is(nullValue()))
                        ))));

    }

    @Test
    void shouldReturnProperDistance_afterAddDistanceToCar(){

        car.setRouteDurationAndDistance(oldRoute);
        Assertions.assertEquals(145.67, car.getDistance());

    }

    @Test
    void shouldReturnProperDistance_afterChangeRoute(){

        car.setRouteDurationAndDistance(oldRoute);
        oldRoute.setCar(car);

        car.updateRouteDurationAndDistance(car, newRoute, oldRoute);

        Assertions.assertEquals(105.67, car.getDistance());

    }

    @Test
    void shouldReturnProperDuration_afterAddRouteToDriver(){

        car.setRouteDurationAndDistance(oldRoute);
        Assertions.assertEquals(123535, car.getDuration());
    }

    @Test
    void shouldReturnProperDuration_afterChangeRoute(){

        car.setRouteDurationAndDistance(oldRoute);
        oldRoute.setCar(car);

        car.updateRouteDurationAndDistance(car, newRoute, oldRoute);

        Assertions.assertEquals(123456, car.getDuration());

    }

}

