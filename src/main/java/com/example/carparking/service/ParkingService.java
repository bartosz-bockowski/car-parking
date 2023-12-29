package com.example.carparking.service;

import com.example.carparking.domain.Car;
import com.example.carparking.domain.Parking;
import com.example.carparking.exception.ForbiddenEngineTypeException;
import com.example.carparking.exception.NotFoundException;
import com.example.carparking.exception.ParkingFullException;
import com.example.carparking.model.ParkingType;
import com.example.carparking.model.VehicleEngineType;
import com.example.carparking.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public Parking save(Parking parking) {
        return parkingRepository.save(parking);
    }

    public Page<Parking> findAll(Pageable pageable) {
        return parkingRepository.findAll(pageable);
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

    public void checkCarCompatibilityWithParking(Car car, Parking parking) {
        if (parking.getCars().size() >= parking.getNumberOfSpaces()) {
            throw new ParkingFullException("Parking of id " + parking.getId() + " is full.");
        }
        if (Objects.equals(parking.getType(), ParkingType.UNDERGROUND) &&
                Objects.equals(car.getEngineType(), VehicleEngineType.LPG)) {
            throw new ForbiddenEngineTypeException("This parking does not allow entrance for cars with LPG engine.");
        }
    }

}
