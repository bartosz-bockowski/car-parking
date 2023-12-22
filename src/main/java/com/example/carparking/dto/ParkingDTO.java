package com.example.carparking.dto;

import com.example.carparking.model.ParkingType;
import lombok.Data;

@Data
public class ParkingDTO {

    private String name;

    private int numberOfSpaces;

    private ParkingType type;

}
