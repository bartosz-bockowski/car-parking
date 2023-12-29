package com.example.carparking.command;

import com.example.carparking.model.VehicleEngineType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CarCommand {

    @NotBlank(message = "car brand name cannot be empty")
    private String brand;

    @NotBlank(message = "car model name cannot be empty")
    private String model;

    @NotNull(message = "car price cannot be null")
    private BigDecimal price;

    @Min(value = 1, message = "car length has to be positive")
    private int lengthInMillimeters;

    @Min(value = 1, message = "car width has to be positive")
    private int widthInMillimeters;

    @NotNull(message = "you have to enter vehicle engine type")
    private VehicleEngineType engineType;

    @Min(value = 1, message = "car production year cannot be negative")
    private int productionYear;

}
