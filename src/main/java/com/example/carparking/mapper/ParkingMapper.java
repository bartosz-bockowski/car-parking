package com.example.carparking.mapper;

import com.example.carparking.command.ParkingCommand;
import com.example.carparking.domain.Parking;
import com.example.carparking.dto.ParkingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
    Parking parkingCommandToParking(ParkingCommand parkingCommand);

    ParkingDTO parkingToParkingDTO(Parking parking);
}
