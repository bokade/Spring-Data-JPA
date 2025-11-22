package com.example.repository;

import com.example.entity.Employeee;
import com.example.projection.EmployeeNameProjection;
import com.example.projection.EmployeeeInfoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeeRepository  extends JpaRepository<Employeee, Long> {
    // 1️⃣ Interface Projection
    List<EmployeeNameProjection> findByDepartment(String department);

    // 2️⃣ DTO via constructor expression (custom query)
    @Query("SELECT new com.example.dto.EmployeeDTO(e.id, e.fullName, e.department) FROM Employeee e")
    List<com.example.dto.EmployeeDTO> getAllEmployeeDTOs();

    // 3️⃣ @Value Projection
    @Query("SELECT e FROM Employeee e WHERE e.salary > :salary")
    List<EmployeeeInfoView> getEmployeesWithSalary(double salary);
}
