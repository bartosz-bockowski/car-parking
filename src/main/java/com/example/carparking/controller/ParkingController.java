package com.example.carparking.controller;

import com.example.carparking.command.ParkingCommand;
import com.example.carparking.dto.ParkingDTO;
import com.example.carparking.mapper.ParkingMapper;
import com.example.carparking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    private final ParkingMapper parkingMapper;

    @PostMapping
    public ResponseEntity<ParkingDTO> save(@Valid @RequestBody ParkingCommand parkingCommand) {
        return new ResponseEntity<>(parkingMapper
                .parkingToParkingDTO(parkingService.save(parkingMapper
                        .parkingCommandToParking(parkingCommand))), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<ParkingDTO> getAll(@SortDefault("id") Pageable pageable) {
        return parkingService.findAll(pageable).stream()
                .map(parkingMapper::parkingToParkingDTO)
                .toList();
    }

    @DeleteMapping("/{parkingId}")
    public HttpStatus delete(@PathVariable Long parkingId) {
        parkingService.deleteById(parkingId);
        return HttpStatus.OK;
    }

}
