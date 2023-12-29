package com.example.carparking.controller;

import com.example.carparking.command.ParkingCommand;
import com.example.carparking.domain.Parking;
import com.example.carparking.exception.NotFoundException;
import com.example.carparking.model.ParkingType;
import com.example.carparking.service.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ParkingControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .build();
    }

    void saveSampleParkings(int numberOfParkings) {
        for (int i = 0; i < numberOfParkings; i++) {
            Parking parking = new Parking("parking2", 100, ParkingType.UNDERGROUND);
            parkingService.save(parking);
        }
    }

    @Test
    void shouldPostParking() throws Exception {
        ParkingCommand parkingCommand = new ParkingCommand("parkingGlowny", 900, ParkingType.ABOVEGROUND);
        this.mockMvc.perform(post("/api/v1/parking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parkingCommand)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(parkingCommand.getName())));
    }

    @Test
    void shouldDeleteParking() throws Exception {
        saveSampleParkings(1);

        this.mockMvc.perform(delete("/api/v1/parking/1"))
                .andExpect(status().isOk());

        Assertions.assertThrows(NotFoundException.class, () -> parkingService.findById(1L));
    }

    @Test
    void shouldGetSecondPageContainingFourElements() throws Exception {
        saveSampleParkings(14);

        this.mockMvc.perform(get("/api/v1/parking/all?page=1"))
                .andExpect(jsonPath("$", hasSize(4)));
    }

}
