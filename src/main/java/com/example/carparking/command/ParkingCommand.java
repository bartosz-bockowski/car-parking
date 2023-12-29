package com.example.carparking.command;

import com.example.carparking.model.ParkingType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ParkingCommand {

    @NotBlank(message = "parking name cannot be empty")
    private String name;

    @Min(value = 1, message = "number of spaces at parking must be positive")
    private int numberOfSpaces;

    @NotNull(message = "you have to enter parking type")
    private ParkingType type;

}
