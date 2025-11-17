package com.example.service;

import com.example.entity.Department;
import com.example.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department updateDepartment(Long id, Department updatedDept) {
        Department existing = departmentRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(updatedDept.getName());
            return departmentRepository.save(existing);
        }
        return null;
    }

    public String deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        return "Department deleted";
    }
}