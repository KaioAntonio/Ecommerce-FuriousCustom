package com.FuriousCustom.domain.dto.vehicle;

import lombok.Data;

@Data
public class VehicleCreateDTO {

    private String name;
    private String model;
    private String brand;
    private Double price;
    private String color;
    private Double kilometers;
}
