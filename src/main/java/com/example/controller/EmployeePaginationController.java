package com.example.controller;

import com.example.entity.EmployeeDynamic;

import com.example.service.EmployeePaginationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emp-pagination")
public class EmployeePaginationController {

    private final EmployeePaginationService service;

    public EmployeePaginationController(EmployeePaginationService service) {
        this.service = service;
    }

    // 1️⃣ Pagination + Sorting (MNC LEVEL)
    @GetMapping("/page")
    public Page<EmployeeDynamic> paginate(
            @RequestParam(defaultValue = "0") int page,        // page number
            @RequestParam(defaultValue = "10") int size,       // page size
            @RequestParam(defaultValue = "id") String sortBy,  // field name
            @RequestParam(defaultValue = "asc") String direction // asc/desc
    ) {
        return service.paginateEmployees(page, size, sortBy, direction);
    }

    // 2️⃣ Multiple sorting fields (MNC)
    @GetMapping("/multi-sort")
    public List<EmployeeDynamic> multiSort(
            @RequestParam List<String> sortFields,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.multiSort(sortFields, direction);
    }
}