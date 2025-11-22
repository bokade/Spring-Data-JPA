package com.example.service.impl;

import com.example.entity.EmployeeAnalytics;
import com.example.repository.EmployeeAnalyticsRepository;
import com.example.service.EmployeeAnalyticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeAnalyticsServiceImpl implements EmployeeAnalyticsService {

    private final EmployeeAnalyticsRepository repo;

    public EmployeeAnalyticsServiceImpl(EmployeeAnalyticsRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeAnalytics saveEmployee(EmployeeAnalytics emp) {
        return repo.save(emp);
    }

    @Override
    public List<Object[]> countByDepartment() {
        return repo.countByDepartment();
    }

    @Override
    public List<Object[]> avgSalaryByDept() {
        return repo.avgSalaryByDept();
    }

    @Override
    public List<Object[]> totalSalaryNative() {
        return repo.totalSalaryByDeptNative();
    }

    @Override
    public List<Object[]> countActiveNative() {
        return repo.countActiveNative();
    }
}
