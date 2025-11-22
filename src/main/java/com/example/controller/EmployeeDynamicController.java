package com.example.controller;

import com.example.entity.EmployeeDynamic;
import com.example.service.EmployeeDynamicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dynamic-emp")
public class EmployeeDynamicController {

    private final EmployeeDynamicService service;

    public EmployeeDynamicController(EmployeeDynamicService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeDynamic save(@RequestBody EmployeeDynamic emp) {
        return service.save(emp);
    }

    // ------- MongoTemplate Style Dynamic Filter -------
    @GetMapping("/search")
    public List<EmployeeDynamic> dynamicSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            @RequestParam(required = false) Boolean active
    ) {
        return service.dynamicFilter(name, minAge, maxAge, minSalary, maxSalary, active);
    }

    // ------- CRITERIA API -------
    @GetMapping("/email")
    public List<EmployeeDynamic> byEmail(@RequestParam String email) {
        return service.criteriaFindByEmail(email);
    }

    @GetMapping("/salary")
    public List<EmployeeDynamic> salaryGreater(@RequestParam Double salary) {
        return service.criteriaSalaryGreater(salary);
    }
}

