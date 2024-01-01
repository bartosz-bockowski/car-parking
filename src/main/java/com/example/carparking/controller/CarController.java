package com.example.carparking.controller;

import com.example.carparking.command.CarCommand;
import com.example.carparking.dto.CarDTO;
import com.example.carparking.mapper.CarMapper;
import com.example.carparking.service.CarService;
import lombok.RequiredArgsConstructor;
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

    private final CarMapper carMapper;

    @PostMapping
    public ResponseEntity<CarDTO> save(@Valid @RequestBody CarCommand carCommand) {
        return new ResponseEntity<>(carMapper
                .carToCarDTO(carService.save(carMapper.carCommandToCar(carCommand))), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<CarDTO> getAll(@SortDefault("id") Pageable pageable) {
        return carService.findAll(pageable).stream()
                .map(carMapper::carToCarDTO)
                .toList();
    }

    @PatchMapping("/{carId}/setParking/{parkingId}")
    public ResponseEntity<CarDTO> setParking(@PathVariable Long carId, @PathVariable Long parkingId) {
        return new ResponseEntity<>(carMapper.carToCarDTO(carService.setParking(carId, parkingId)), HttpStatus.OK);
    }

    @DeleteMapping("/{carId}")
    public HttpStatus delete(@PathVariable Long carId) {
        carService.deleteById(carId);
        return HttpStatus.OK;
    }

}
