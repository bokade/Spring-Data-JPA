package com.example.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmployeeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double salary;
    private String email;
    private Integer age;
    private Boolean active;
}

