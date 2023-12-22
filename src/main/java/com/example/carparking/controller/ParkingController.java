package com.example.carparking.controller;

import com.example.carparking.command.ParkingCommand;
import com.example.carparking.domain.Parking;
import com.example.carparking.dto.ParkingDTO;
import com.example.carparking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ParkingDTO> save(@Valid @RequestBody ParkingCommand parkingCommand) {
        return new ResponseEntity<>(modelMapper
                .map(parkingService.save(
                        modelMapper.map(parkingCommand, Parking.class)), ParkingDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{parkingId}")
    public HttpStatus delete(@PathVariable Long parkingId) {
        parkingService.deleteById(parkingId);
        return HttpStatus.OK;
    }
    
}
