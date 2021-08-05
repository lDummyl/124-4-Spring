package com.example.demo.db.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "CAR")
@Getter
@Setter
@ToString
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modelName;

    private String carName;

    private String description;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DriverEntity driver;
}
