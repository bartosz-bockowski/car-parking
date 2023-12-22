package com.example.carparking.service;

import com.example.carparking.domain.Car;
import com.example.carparking.domain.Parking;
import com.example.carparking.exception.NotFoundException;
import com.example.carparking.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final ParkingService parkingService;

    public Car findById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car with id " + carId + " not found."));
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car update(Car car) {
        return carRepository.save(car);
    }

    public Car setParking(Long carId, Long parkingId) {
        Car car = findById(carId);
        Parking parking = parkingService.findById(parkingId);
        parking.checkCar(car);
        car.setParking(parking);
        return update(car);
    }

    public void deleteById(Long carId) {
        carRepository.deleteById(carId);
    }

}
