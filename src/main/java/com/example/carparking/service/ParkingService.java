package com.example.carparking.service;

import com.example.carparking.domain.Parking;
import com.example.carparking.exception.NotFoundException;
import com.example.carparking.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public Parking save(Parking parking) {
        return parkingRepository.save(parking);
    }

    public Parking findById(Long parkingId) {
        return parkingRepository.findById(parkingId)
                .orElseThrow(() -> new NotFoundException("Parking with id " + parkingId + " not found."));
    }

    public void deleteById(Long parkingId) {
        Parking parking = findById(parkingId);
        parking.setCars(null);
        save(parking);
        parkingRepository.delete(parking);
    }

}
