package com.example.autoservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String year;
    private String number;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
