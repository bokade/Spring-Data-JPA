package com.example.service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.repository.DepartmentRepository;
import com.example.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository deptRepo;

    public EmployeeService(EmployeeRepository employeeRepo, DepartmentRepository deptRepo) {
        this.employeeRepo = employeeRepo;
        this.deptRepo = deptRepo;
    }

    public Employee saveEmployee(Employee emp, Long deptId) {
        Department dept = deptRepo.findById(deptId).orElse(null);
        emp.setDepartment(dept);
        return employeeRepo.save(emp);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee e) {
        Employee existing = employeeRepo.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(e.getName());
            existing.setSalary(e.getSalary());
            return employeeRepo.save(existing);
        }
        return null;
    }

    public String deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
        return "Employee deleted";
    }
}
