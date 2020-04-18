package pl.com.navcity.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.com.navcity.model.Driver;
import pl.com.navcity.model.Route;
import pl.com.navcity.service.DriverServiceImpl;
import pl.com.navcity.service.RouteServiceImpl;
import pl.com.navcity.service.UserDetailsServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DriverController.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class DriverControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    DriverServiceImpl driverService;

    @MockBean
    RouteServiceImpl routeService;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    Route oldRoute;
    Route newRoute;
    Driver driver;

    @BeforeEach
    void setUp(){
        driver = new Driver(1, "John","Smith","ger65748");
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
    @WithMockUser(username = "Dominik", roles = {"ADMIN"})
    void resultShouldMatchDriver_whenConnectToEndpoint() throws Exception {

        BDDMockito.given(driverService.getAllDrivers()).willReturn(Arrays.asList(driver));

        mockMvc.perform(get("/api/drivers/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listOfDrivers", hasSize(1)))
                .andExpect(model().attribute("listOfDrivers", hasItem(
                        allOf(
                                hasProperty("id",is(1)),
                                hasProperty("firstName",is("John")),
                                hasProperty("lastName",is("Smith")),
                                hasProperty("licence",is("ger65748"))

                        ))));

        BDDMockito.verify(driverService, Mockito.times(1)).getAllDrivers();
        BDDMockito.verifyNoMoreInteractions(driverService);
    }


    @Test
    void shouldReturnProperDistance_afterAddRouteToDriver(){

        driver.setRouteDurationAndDistance(oldRoute);
        Assertions.assertEquals(145.67, driver.getDistance());
    }

    @Test
    void shouldReturnProperDistance_afterChangeRoute(){

        driver.setRouteDurationAndDistance(oldRoute);
        oldRoute.setDriver(driver);

        driver.updateRouteDurationAndDistance(driver, newRoute, oldRoute);

        Assertions.assertEquals(105.67, driver.getDistance());

    }
}
