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


    @Test
    @WithMockUser(username = "Dominik", roles = {"ADMIN", "MANAGER"})
    void resultShouldMatchCar_whenConnectToEndpoint() throws Exception {

        Car car = new Car("Porsche", "Panamera", "5443123221234", Color.Black, 2011, null);
        BDDMockito.given(carService.getAllCars()).willReturn(Arrays.asList(car));

        mockMvc.perform(get("/api/cars/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listOfCars", hasSize(1)))
                .andExpect(model().attribute("listOfCars", hasItem(
                        allOf(
                                hasProperty("brand", is("Porsche")),
                                hasProperty("model", is("Panamera")),
                                hasProperty("vinNumber", is("5443123221234")),
                                hasProperty("color", is(Color.Black)),
                                hasProperty("productionYear", is(2011)),
                                hasProperty("notes", is(nullValue()))
                        ))));

    }

    @Test
    void shouldReturnProperValue_afterAddDistanceToCar(){

        Car car = new Car("Porsche", "Panamera", "5443123221234", Color.Black, 2011, null);
        Route route = new Route();
        route.setDistance(103560.00);

        car.setRouteDurationAndDistance(route);

        Assertions.assertEquals(103.56, car.getDistance());

    }

        @Test
        void shouldReturnDistanceEqualsToZero_afterdeleteRoute(){
            Car car = new Car("Porsche", "Panamera", "5443123221234", Color.Black, 2011, null);
            Route route = new Route();
            car.deleteRouteAndUpdateTimeDistance(route);

            Assertions.assertEquals(0.00, car.getDistance());

        }


}
