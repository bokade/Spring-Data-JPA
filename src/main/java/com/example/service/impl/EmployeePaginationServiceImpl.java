package com.example.service.impl;

import com.example.entity.EmployeeDynamic;
import com.example.repository.EmployeeDynamicRepository;
import com.example.service.EmployeePaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePaginationServiceImpl implements EmployeePaginationService {

    private final EmployeeDynamicRepository repo;

    public EmployeePaginationServiceImpl(EmployeeDynamicRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<EmployeeDynamic> paginateEmployees(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repo.findAll(pageable);
    }

    @Override
    public List<EmployeeDynamic> multiSort(List<String> fields, String direction) {

        List<Sort.Order> orders = new ArrayList<>();

        for (String f : fields) {
            orders.add(
                    direction.equalsIgnoreCase("desc") ?
                            Sort.Order.desc(f) :
                            Sort.Order.asc(f)
            );
        }

        return repo.findAll(Sort.by(orders));
    }
}