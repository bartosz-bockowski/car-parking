package com.example.carparking.domain;

import com.example.carparking.model.ParkingType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Parking {

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

    @NotBlank(message = "parking name cannot be empty")
    private String name;

    @Min(value = 1, message = "number of spaces at parking must be positive")
    private int numberOfSpaces;

    @NotNull(message = "you have to enter parking type")
    private ParkingType type;

}
