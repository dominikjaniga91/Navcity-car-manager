package pl.com.navcity.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.com.navcity.model.Route;
import pl.com.navcity.service.CarServiceImpl;
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
@WebMvcTest(value = RouteController.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class RouteControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    RouteServiceImpl routeService;

    @MockBean
    CarServiceImpl carService;

    @MockBean
    DriverServiceImpl driverService;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp(){
        Route route = new Route("Zakopianka",
                LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(10,0,0)),
                LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(12,0,0)),
                "Kraków", "Zakopane");
        BDDMockito.given(routeService.getAllRoutes()).willReturn(Arrays.asList(route));
    }

    @Test
    @WithMockUser(username = "Damian", roles = {"ADMIN"})
    void shouldMatchRoute_afterHitToEndpointGetAllRoutes() throws Exception{

        mockMvc.perform(get("/api/routes/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listOfRoutes", hasItem(
                        allOf(
                             hasProperty("routeName", is("Zakopianka")),
                             hasProperty("departureDate", is(LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(10,0,0)))),
                             hasProperty("arrivalDate", is(LocalDateTime.of(LocalDate.of(2020, 5,5), LocalTime.of(12,0,0)))),
                             hasProperty("departureAddress", is("Kraków")),
                             hasProperty("destinationAddress", is("Zakopane"))

                        ))));
    }
}
