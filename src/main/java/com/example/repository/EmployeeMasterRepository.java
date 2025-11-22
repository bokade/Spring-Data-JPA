package com.example.repository;

import com.example.entity.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Long> {

    // ----------- Derived Queries -----------

    List<EmployeeMaster> findByName(String name);

    EmployeeMaster findByEmail(String email);

    List<EmployeeMaster> findByAgeGreaterThan(Integer age);

    List<EmployeeMaster> findBySalaryBetween(Double min, Double max);

    List<EmployeeMaster> findByActiveTrue();

    List<EmployeeMaster> findByNameContainingIgnoreCase(String namePart);

    List<EmployeeMaster> findByAgeIn(List<Integer> ages);

    boolean existsByEmail(String email);  // interview favourite
}
