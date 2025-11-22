package com.example.service;

import com.example.entity.EmployeeAnalytics;

import java.util.List;

public interface EmployeeAnalyticsService {
    EmployeeAnalytics saveEmployee(EmployeeAnalytics emp);
    List<Object[]> countByDepartment();
    List<Object[]> avgSalaryByDept();
    List<Object[]> totalSalaryNative();
    List<Object[]> countActiveNative();
}