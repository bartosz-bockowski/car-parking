package com.example.carparking.dto;

import com.example.carparking.model.VehicleEngineType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDTO {

    private ParkingDTO parkingDTO;

    private String brand;

    private String model;

    private BigDecimal price;

    private int lengthInMillimeters;

    private int widthInMillimeters;

    private VehicleEngineType engineType;

    private int productionYear;

}
