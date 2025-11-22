package com.example.projection;

import org.springframework.beans.factory.annotation.Value;

public class EmployeeeInfoView {
    private final String fullName;
    private final double salary;

    public EmployeeeInfoView(@Value("#{target.fullName}") String fullName, @Value("#{target.salary}") double salary) {
        this.fullName = fullName;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public double getSalary() {
        return salary;
    }
}
