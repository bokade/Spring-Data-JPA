package com.example.service.propogation;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employeee;
import com.example.projection.EmployeeNameProjection;
import com.example.projection.EmployeeeInfoView;
import com.example.repository.EmployeeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeeService {
    private final EmployeeeRepository repo;

    public EmployeeeService(EmployeeeRepository repo) {
        this.repo = repo;
    }

    // 1️⃣ Save
    public Employeee save(Employeee emp) {
        return repo.save(emp);
    }

    // 2️⃣ DTO Mapping
    public List<EmployeeDTO> getAllDTOs() {
        return repo.getAllEmployeeDTOs();
    }

    // 3️⃣ Interface Projection
    public List<EmployeeNameProjection> getNameProjection(String department) {
        return repo.findByDepartment(department);
    }

    // 4️⃣ @Value Projection
    public List<EmployeeeInfoView> getSalaryProjection(double salary) {
        return repo.getEmployeesWithSalary(salary);
    }
}
