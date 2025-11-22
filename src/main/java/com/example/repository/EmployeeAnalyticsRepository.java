package com.example.repository;

import com.example.entity.EmployeeAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAnalyticsRepository extends JpaRepository<EmployeeAnalytics, Long> {

    // 1️⃣ JPQL uses Entity name (NOT table name)
    @Query("SELECT e.department, COUNT(e) FROM EmployeeAnalytics e GROUP BY e.department")
    List<Object[]> countByDepartment();

    // 2️⃣ JPQL for AVG
    @Query("SELECT e.department, AVG(e.salary) FROM EmployeeAnalytics e GROUP BY e.department")
    List<Object[]> avgSalaryByDept();

    // 3️⃣ Native SQL uses TABLE NAME (employee_analytics)
    @Query(value = "SELECT department, SUM(salary) AS totalSalary " +
            "FROM employee_analytics GROUP BY department",
            nativeQuery = true)
    List<Object[]> totalSalaryByDeptNative();

    // 4️⃣ Native SQL - active vs inactive
    @Query(value = "SELECT active, COUNT(*) FROM employee_analytics GROUP BY active",
            nativeQuery = true)
    List<Object[]> countActiveNative();
}