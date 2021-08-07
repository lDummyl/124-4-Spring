package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String phone;
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
