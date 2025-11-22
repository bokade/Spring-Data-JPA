package com.example.service;

import com.example.entity.EmployeeMaster;
import com.example.repository.EmployeeMasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeMasterService {

    private final EmployeeMasterRepository repo;

    public EmployeeMasterService(EmployeeMasterRepository repo) {
        this.repo = repo;
    }

    // ---------------- DEFAULT CRUD METHODS ----------------

    public EmployeeMaster save(EmployeeMaster emp) {
        return repo.save(emp);
    }

    public List<EmployeeMaster> saveAll(List<EmployeeMaster> employees) {
        return repo.saveAll(employees);
    }

    public EmployeeMaster getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<EmployeeMaster> getAll() {
        return repo.findAll();
    }

    public String deleteById(Long id) {
        repo.deleteById(id);
        return "Deleted " + id;
    }

    public boolean exists(Long id) {
        return repo.existsById(id);
    }

    public long count() {
        return repo.count();
    }

    public void flushData() {
        repo.flush();
    }

    // ---------------- DERIVED QUERY METHODS ----------------

    public List<EmployeeMaster> getByName(String name) {
        return repo.findByName(name);
    }

    public EmployeeMaster getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<EmployeeMaster> ageGreaterThan(Integer age) {
        return repo.findByAgeGreaterThan(age);
    }

    public List<EmployeeMaster> salaryBetween(Double min, Double max) {
        return repo.findBySalaryBetween(min, max);
    }

    public List<EmployeeMaster> activeEmployees() {
        return repo.findByActiveTrue();
    }

    public List<EmployeeMaster> searchByName(String namePart) {
        return repo.findByNameContainingIgnoreCase(namePart);
    }

    public List<EmployeeMaster> filterByAges(List<Integer> ages) {
        return repo.findByAgeIn(ages);
    }

    public boolean emailExists(String email) {
        return repo.existsByEmail(email);
    }
}
