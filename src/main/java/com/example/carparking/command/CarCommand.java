package com.example.carparking.command;

import com.example.carparking.model.VehicleEngineType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CarCommand {

    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @NotNull
    private BigDecimal price;

    private int lengthInMillimeters;

    private int widthInMillimeters;

    @NotNull
    private VehicleEngineType engineType;

    private int productionYear;

}
