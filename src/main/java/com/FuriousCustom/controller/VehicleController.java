package com.FuriousCustom.controller;

import com.FuriousCustom.domain.dto.PageDTO;
import com.FuriousCustom.domain.dto.vehicle.VehicleCreateDTO;
import com.FuriousCustom.domain.dto.vehicle.VehicleDTO;
import com.FuriousCustom.domain.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicle")
@Validated
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(description = "Create a Vehicle", summary = "Create a Vehicle")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Create a Vehicle"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception was generated")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> create(@RequestBody @Valid VehicleCreateDTO vehicleCreateDTO){
        return new ResponseEntity<>(vehicleService.create(vehicleCreateDTO), HttpStatus.CREATED);
    }

    @Operation(description = "Find all Vehicles", summary = "Find all Vehicles")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find all Vehicles"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception was generated")
            }
    )
    @GetMapping("/findAll")
    public ResponseEntity<PageDTO<VehicleDTO>> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ){
        return new ResponseEntity<>(vehicleService.findAll(page,size), HttpStatus.OK);
    }



}
