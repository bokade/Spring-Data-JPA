package com.example.service;

import com.example.entity.EmployeeQuery;

import java.util.List;

public interface EmployeeQueryService {

    EmployeeQuery save(EmployeeQuery emp);

    EmployeeQuery findByEmailJPQL(String email);

    List<EmployeeQuery> getAgeGreaterJPQL(Integer age);

    List<EmployeeQuery> searchName(String key);

    List<EmployeeQuery> salaryBetweenNative(Double min, Double max);

    List<EmployeeQuery> activeEmployeesNative();

    String updateSalary(Long id, Double salary);

    String deleteByEmail(String email);
}