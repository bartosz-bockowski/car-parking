package com.example.carparking.command;

import com.example.carparking.model.ParkingType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ParkingCommand {

    @NotEmpty
    private String name;

    private int numberOfSpaces;

    @NotNull
    private ParkingType type;

}
