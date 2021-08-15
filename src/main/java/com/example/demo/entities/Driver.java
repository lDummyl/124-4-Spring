package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String phone;

    @Temporal(TemporalType.DATE)
    private Calendar birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Car> cars;

}
