package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Car {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    private String brand;
    private String model;
    private String description;

    @ManyToOne
    private Driver driver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return this.id + ". Brand: " + this.brand + "| Model: " + this.model
                + "| Description: " + this.description;
    }
}
