package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany
    private List<Car> cars = new LinkedList<>();

    public void update(Garage garage){
        if(garage.getId() != null)
            this.id = garage.getId();
        if(garage.getAddress() != null)
            this.address = garage.getAddress();
        if(garage.getCars() != null)
            this.cars = garage.getCars();
    }

}
