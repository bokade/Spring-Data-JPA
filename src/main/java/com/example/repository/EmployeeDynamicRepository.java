package com.example.repository;

import com.example.entity.EmployeeDynamic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeDynamicRepository extends JpaRepository<EmployeeDynamic, Long>, JpaSpecificationExecutor<EmployeeDynamic> {
}
