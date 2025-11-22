package com.example.controller;

import com.example.entity.EmployeeAnalytics;
import com.example.service.EmployeeAnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp-analytics")
public class EmployeeAnalyticsController {

    private final EmployeeAnalyticsService service;

    public EmployeeAnalyticsController(EmployeeAnalyticsService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public EmployeeAnalytics saveEmployee(@RequestBody EmployeeAnalytics emp) {
        return service.saveEmployee(emp);
    }

    // 1️⃣ Count employees department-wise (JPQL)
    @GetMapping("/count-dept")
    public List<Object[]> countByDepartment() {
        return service.countByDepartment();
    }

    // 2️⃣ Average salary per department (JPQL)
    @GetMapping("/avg-salary")
    public List<Object[]> avgSalaryByDept() {
        return service.avgSalaryByDept();
    }

    // 3️⃣ Total salary per department (Native SQL)
    @GetMapping("/total-salary")
    public List<Object[]> totalDeptSalary() {
        return service.totalSalaryNative();
    }

    // 4️⃣ Active vs Inactive employee count (Native SQL)
    @GetMapping("/active-status")
    public List<Object[]> activeStatusCount() {
        return service.countActiveNative();
    }
}
