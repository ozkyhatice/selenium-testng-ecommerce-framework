package com.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Product {
    private int id;
    private String name;
    private String price;
    private String brand;
}
