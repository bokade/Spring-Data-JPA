package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employeee;
import com.example.projection.EmployeeNameProjection;
import com.example.projection.EmployeeeInfoView;
import com.example.service.propogation.EmployeeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
public class EmployeeeController {

    @Autowired
    private EmployeeeService service;

    public EmployeeeController(EmployeeeService service) {
        this.service = service;
    }

    // 1️⃣ Save
    @PostMapping("/save")
    public Employeee save(@RequestBody Employeee emp) {
        return service.save(emp);
    }

    // 2️⃣ DTO Mapping
    @GetMapping("/dto")
    public List<EmployeeDTO> getDTOs() {
        return service.getAllDTOs();
    }

    // 3️⃣ Interface Projection
    @GetMapping("/name/{dept}")
    public List<EmployeeNameProjection> nameProjection(@PathVariable String dept) {
        return service.getNameProjection(dept);
    }

    // 4️⃣ Value Projection
    @GetMapping("/salary/{amount}")
    public List<EmployeeeInfoView> salaryProjection(@PathVariable double amount) {
        return service.getSalaryProjection(amount);
    }
}
