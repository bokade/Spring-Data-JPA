package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmployeeDynamic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Integer age;
    private Double salary;
    private Boolean active;
}
