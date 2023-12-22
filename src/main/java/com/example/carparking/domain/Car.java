package com.example.carparking.domain;

import com.example.carparking.model.VehicleEngineType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Car {

    public Car() {

    }

    public Car(String brand, String model, BigDecimal price, int lengthInMillimeters, int widthInMillimeters, VehicleEngineType engineType, int productionYear) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.lengthInMillimeters = lengthInMillimeters;
        this.widthInMillimeters = widthInMillimeters;
        this.engineType = engineType;
        this.productionYear = productionYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Parking parking;

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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", lengthInMillimeters=" + lengthInMillimeters +
                ", widthInMillimeters=" + widthInMillimeters +
                ", engineType=" + engineType +
                ", productionYear=" + productionYear +
                '}';
    }

}
