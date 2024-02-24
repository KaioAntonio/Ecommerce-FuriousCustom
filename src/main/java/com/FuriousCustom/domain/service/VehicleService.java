package com.FuriousCustom.domain.service;

import com.FuriousCustom.domain.dto.vehicle.VehicleCreateDTO;
import com.FuriousCustom.domain.dto.vehicle.VehicleDTO;
import com.FuriousCustom.domain.entity.Vehicle;
import com.FuriousCustom.domain.repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ObjectMapper objectMapper;

    public VehicleDTO create(VehicleCreateDTO vehicleCreateDTO) {
        Vehicle vehicle = objectMapper.convertValue(vehicleCreateDTO, Vehicle.class);
        return objectMapper.convertValue(vehicleRepository.save(vehicle), VehicleDTO.class);
    }

}
