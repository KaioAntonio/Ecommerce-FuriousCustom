package com.FuriousCustom.domain.dto.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class VehicleCreateDTO {

    @NotBlank
    @Size(min = 4, max = 200)
    @Schema(description = "Vehicle Name" , example = "vehicle")
    private String name;
    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "Vehicle model" , example = "c63")
    private String model;
    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "Vehicle brand" , example = "Mercedes")
    private String brand;
    @NotNull
    @Schema(description = "Vehicle price" , example = "10000")
    private Double price;
    @NotNull
    @Schema(description = "Vehicle color" , example = "blue")
    private String color;
    @NotNull
    @Schema(description = "Vehicle kilometers" , example = "1000")
    private Double kilometers;
}
