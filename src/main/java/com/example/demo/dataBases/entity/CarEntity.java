package com.example.demo.dataBases.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "public")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
}