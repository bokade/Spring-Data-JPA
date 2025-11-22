package com.example.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "employee_analytics")
@Data
public class EmployeeAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private Double salary;
    private Integer age;
    private Boolean active;
}
