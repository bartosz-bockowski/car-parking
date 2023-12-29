package com.example.carparking.domain;

import com.example.carparking.model.VehicleEngineType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Parking parking;

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

    public Car(String brand, String model, BigDecimal price, int lengthInMillimeters, int widthInMillimeters, VehicleEngineType engineType, int productionYear) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.lengthInMillimeters = lengthInMillimeters;
        this.widthInMillimeters = widthInMillimeters;
        this.engineType = engineType;
        this.productionYear = productionYear;
    }

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
