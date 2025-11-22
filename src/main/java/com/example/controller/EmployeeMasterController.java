package com.example.controller;

import com.example.entity.EmployeeMaster;
import com.example.service.EmployeeMasterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp-master")
public class EmployeeMasterController {

    private final EmployeeMasterService service;

    public EmployeeMasterController(EmployeeMasterService service) {
        this.service = service;
    }

    // ---------------- DEFAULT CRUD ----------------

    @PostMapping
    public EmployeeMaster save(@RequestBody EmployeeMaster emp) {
        return service.save(emp);
    }

    @PostMapping("/bulk")
    public List<EmployeeMaster> saveAll(@RequestBody List<EmployeeMaster> employees) {
        return service.saveAll(employees);
    }

    @GetMapping("/{id}")
    public EmployeeMaster getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<EmployeeMaster> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return service.exists(id);
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @PostMapping("/flush")
    public String flush() {
        service.flushData();
        return "Flushed";
    }

    // ---------------- DERIVED QUERIES ----------------

    @GetMapping("/name/{name}")
    public List<EmployeeMaster> getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("/email/{email}")
    public EmployeeMaster getByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

    @GetMapping("/age/{age}")
    public List<EmployeeMaster> getAgeGreater(@PathVariable Integer age) {
        return service.ageGreaterThan(age);
    }

    @GetMapping("/salary")
    public List<EmployeeMaster> salaryBetween(
            @RequestParam Double min,
            @RequestParam Double max) {
        return service.salaryBetween(min, max);
    }

    @GetMapping("/active")
    public List<EmployeeMaster> activeEmployees() {
        return service.activeEmployees();
    }

    @GetMapping("/search")
    public List<EmployeeMaster> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

    @PostMapping("/age-list")
    public List<EmployeeMaster> filterByAges(@RequestBody List<Integer> ages) {
        return service.filterByAges(ages);
    }

    @GetMapping("/exists/email")
    public boolean existsByEmail(@RequestParam String email) {
        return service.emailExists(email);
    }
}
