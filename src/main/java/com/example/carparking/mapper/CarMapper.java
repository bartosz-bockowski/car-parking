package com.example.carparking.mapper;

import com.example.carparking.command.CarCommand;
import com.example.carparking.domain.Car;
import com.example.carparking.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO carToCarDTO(Car car);

    Car carCommandToCar(CarCommand carCommand);
}
