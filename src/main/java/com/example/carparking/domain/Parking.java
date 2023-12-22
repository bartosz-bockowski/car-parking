package com.example.carparking.domain;

import com.example.carparking.exception.ForbiddenEngineTypeException;
import com.example.carparking.exception.ParkingFullException;
import com.example.carparking.model.ParkingType;
import com.example.carparking.model.VehicleEngineType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Parking {

    public Parking() {

    }

    public Parking(String name, int numberOfSpaces, ParkingType type) {
        this.name = name;
        this.numberOfSpaces = numberOfSpaces;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parking", fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();

    @NotEmpty
    private String name;

    private int numberOfSpaces;

    @NotNull
    private ParkingType type;

    public void checkCar(Car car) {
        if (this.cars.size() >= this.numberOfSpaces) {
            throw new ParkingFullException("Parking of id " + this.id + " is full.");
        }
        if (Objects.equals(this.type, ParkingType.UNDERGROUND) &&
                Objects.equals(car.getEngineType(), VehicleEngineType.LPG)) {
            throw new ForbiddenEngineTypeException("This parking does not allow entrance for cars with LPG engine.");
        }
    }

}
