package com.example.carparking.controller;

import com.example.carparking.command.CarCommand;
import com.example.carparking.domain.Car;
import com.example.carparking.domain.Parking;
import com.example.carparking.model.ParkingType;
import com.example.carparking.model.VehicleEngineType;
import com.example.carparking.service.CarService;
import com.example.carparking.service.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private ParkingService parkingService;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    void shouldPostCar() throws Exception {
        CarCommand carCommand = new CarCommand("brand1", "model1", new BigDecimal("35193.30"), 5000, 2000, VehicleEngineType.DIESEL, 2010);
        this.mockMvc.perform(post("/api/v1/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carCommand)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand", equalTo(carCommand.getBrand())));
    }

    @Test
    void shouldSetParking() throws Exception {
        Car car1 = new Car("brand1", "model1", new BigDecimal("35193.30"), 5000, 2000, VehicleEngineType.LPG, 2010);
        carService.save(car1);

        Parking parking = new Parking("parkingGlowny", 2, ParkingType.ABOVEGROUND);
        parkingService.save(parking);

        this.mockMvc.perform(patch("/api/v1/car/1/setParking/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotAcceptableForbiddenEngine() throws Exception {
        Car car = new Car("brand1", "model1", new BigDecimal("35193.30"), 5000, 2000, VehicleEngineType.LPG, 2010);
        carService.save(car);

        Parking parking = new Parking("parkingGlowny", 100, ParkingType.UNDERGROUND);
        parkingService.save(parking);

        this.mockMvc.perform(patch("/api/v1/car/1/setParking/1"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void shouldReturnBadRequestFullParking() throws Exception {
        Car car1 = new Car("brand1", "model1", new BigDecimal("35193.30"), 5000, 2000, VehicleEngineType.LPG, 2010);
        Car car2 = new Car("brand1", "model1", new BigDecimal("35193.30"), 5000, 2000, VehicleEngineType.LPG, 2010);
        Car car3 = new Car("brand1", "model1", new BigDecimal("35193.30"), 5000, 2000, VehicleEngineType.LPG, 2010);
        carService.save(car1);
        carService.save(car2);
        carService.save(car3);

        Parking parking = new Parking("parkingGlowny", 2, ParkingType.ABOVEGROUND);
        parkingService.save(parking);

        carService.setParking(1L, 1L);
        carService.setParking(2L, 1L);
        this.mockMvc.perform(patch("/api/v1/car/1/setParking/1"))
                .andExpect(status().isBadRequest());
    }

}
