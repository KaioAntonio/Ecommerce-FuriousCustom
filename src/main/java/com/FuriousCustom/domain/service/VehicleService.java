package com.FuriousCustom.domain.service;

import com.FuriousCustom.config.exception.ServiceException;
import com.FuriousCustom.domain.dto.PageDTO;
import com.FuriousCustom.domain.dto.vehicle.VehicleCreateDTO;
import com.FuriousCustom.domain.dto.vehicle.VehicleDTO;
import com.FuriousCustom.domain.entity.Vehicle;
import com.FuriousCustom.domain.repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public VehicleDTO create(VehicleCreateDTO vehicleCreateDTO) {
        Vehicle vehicle = objectMapper.convertValue(vehicleCreateDTO, Vehicle.class);
        return objectMapper.convertValue(vehicleRepository.save(vehicle), VehicleDTO.class);
    }

    public Vehicle findById(Integer id) throws ServiceException {
        return vehicleRepository.findById(id).orElseThrow(() -> new ServiceException("Veículo não encontrado!"));
    }

    public PageDTO<VehicleDTO> findAll(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);
        List<VehicleDTO> vehicleDTOS = vehiclePage.getContent().stream()
                .map(vehicle -> objectMapper.convertValue(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());

        return new PageDTO<>(vehiclePage.getTotalElements(),
                vehiclePage.getTotalPages(),
                page,
                size,
                vehicleDTOS);

    }

    public VehicleDTO update(VehicleCreateDTO vehicleCreateDTO, Integer id) throws ServiceException {
        Vehicle vehicle = findById(id);
        modelMapper.map(vehicleCreateDTO, vehicle);
        vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    public void delete(Integer id) throws ServiceException {
        vehicleRepository.delete(findById(id));
    }

}
