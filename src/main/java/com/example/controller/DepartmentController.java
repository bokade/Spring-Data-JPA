package com.example.controller;

import com.example.entity.Department;
import com.example.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public Department create(@RequestBody Department dept) {
        return service.saveDepartment(dept);
    }

    @GetMapping
    public List<Department> getAll() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return service.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department dept) {
        return service.updateDepartment(id, dept);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteDepartment(id);
    }
}

