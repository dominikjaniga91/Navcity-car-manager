package pl.com.navcity;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.com.navcity.controller.CarController;
import pl.com.navcity.model.Car;
import pl.com.navcity.model.Color;
import pl.com.navcity.repository.CarRepositoryImpl;
import pl.com.navcity.service.CarServiceImpl;
import pl.com.navcity.service.RouteServiceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
class NavcityTrialApplicationTests {


   @Autowired
   MockMvc mockMvc;

   @MockBean
   CarServiceImpl carService;

   @MockBean
   RouteServiceImpl routeService;

    @BeforeEach
    void setUp() {
        Car car = new Car("Porche", "Panamera", "5443123221234", Color.Black, 2011, null);
        BDDMockito.given(carService.getAllCars()).willReturn(List.of(car));
    }

    @Test
    void contextLoads() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/carList")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("Porsche")));

        List<String> dfsf= new LinkedList<>();
    }

    int a =2;

    @Test
    void shouldReturnValueOfTwo(){

        Assertions.assertEquals(2,a);
    }
}
