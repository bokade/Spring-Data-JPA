package com.example.controller;

import com.example.entity.EmployeeQuery;
import com.example.service.EmployeeQueryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/query-emp")
public class EmployeeQueryController {

    private final EmployeeQueryService service;

    public EmployeeQueryController(EmployeeQueryService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeQuery save(@RequestBody EmployeeQuery emp) {
        return service.save(emp);
    }

    @GetMapping("/email/{email}")
    public EmployeeQuery getByEmail(@PathVariable String email) {
        return service.findByEmailJPQL(email);
    }

    @GetMapping("/age/{age}")
    public List<EmployeeQuery> ageGreater(@PathVariable Integer age) {
        return service.getAgeGreaterJPQL(age);
    }

    @GetMapping("/search")
    public List<EmployeeQuery> search(@RequestParam String key) {
        return service.searchName(key);
    }

    @GetMapping("/salary")
    public List<EmployeeQuery> salaryBetween(@RequestParam Double min,
                                             @RequestParam Double max) {
        return service.salaryBetweenNative(min, max);
    }

    @GetMapping("/active")
    public List<EmployeeQuery> active() {
        return service.activeEmployeesNative();
    }

    @PutMapping("/salary/{id}")
    public String updateSalary(@PathVariable Long id,
                               @RequestParam Double salary) {
        return service.updateSalary(id, salary);
    }

    @DeleteMapping("/email/{email}")
    public String delete(@PathVariable String email) {
        return service.deleteByEmail(email);
    }
}