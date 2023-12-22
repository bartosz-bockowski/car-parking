package com.example.carparking.command;

import com.example.carparking.model.ParkingType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ParkingCommand {

    public ParkingCommand(String name, int numberOfSpaces, ParkingType type) {
        this.name = name;
        this.numberOfSpaces = numberOfSpaces;
        this.type = type;
    }

    @NotEmpty
    private String name;

    private int numberOfSpaces;

    @NotNull
    private ParkingType type;

}
