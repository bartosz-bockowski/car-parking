package com.example.carparking.controller;

import com.example.carparking.command.CarCommand;
import com.example.carparking.domain.Car;
import com.example.carparking.dto.CarDTO;
import com.example.carparking.service.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CarDTO> save(@Valid @RequestBody CarCommand carCommand) {
        return new ResponseEntity<>(modelMapper
                .map(carService.save(modelMapper
                        .map(carCommand, Car.class)), CarDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<CarDTO> getAll(@SortDefault("id") Pageable pageable) {
        return carService.findAll(pageable).stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();
    }

    @PatchMapping("/{carId}/setParking/{parkingId}")
    public ResponseEntity<CarDTO> setParking(@PathVariable Long carId, @PathVariable Long parkingId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return new ResponseEntity<>(modelMapper
                .map(carService.setParking(carId, parkingId), CarDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{carId}")
    public HttpStatus delete(@PathVariable Long carId) {
        carService.deleteById(carId);
        return HttpStatus.OK;
    }

}
