package com.example.service;

import com.example.entity.EmployeeDynamic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeePaginationService {
    Page<EmployeeDynamic> paginateEmployees(int page, int size, String sortBy, String direction);
    List<EmployeeDynamic> multiSort(List<String> fields, String direction);
}