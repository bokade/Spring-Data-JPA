package com.example.service.impl;

import com.example.entity.EmployeeQuery;

import com.example.repository.EmployeeQueryRepository;
import com.example.service.EmployeeQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final EmployeeQueryRepository repo;

    public EmployeeQueryServiceImpl(EmployeeQueryRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeQuery save(EmployeeQuery emp) {
        return repo.save(emp);
    }

    @Override
    public EmployeeQuery findByEmailJPQL(String email) {
        return repo.findByEmailJPQL(email);
    }

    @Override
    public List<EmployeeQuery> getAgeGreaterJPQL(Integer age) {
        return repo.getAgeGreaterJPQL(age);
    }

    @Override
    public List<EmployeeQuery> searchName(String key) {
        return repo.searchNameJPQL(key);
    }

    @Override
    public List<EmployeeQuery> salaryBetweenNative(Double min, Double max) {
        return repo.salaryBetweenNative(min, max);
    }

    @Override
    public List<EmployeeQuery> activeEmployeesNative() {
        return repo.activeEmployeesNative();
    }

    @Override
    public String updateSalary(Long id, Double salary) {
        int updated = repo.updateSalary(id, salary);
        return updated > 0 ? "Salary Updated" : "Employee Not Found";
    }

    @Override
    public String deleteByEmail(String email) {
        int deleted = repo.deleteByEmailJPQL(email);
        return deleted > 0 ? "Deleted Successfully" : "Email Not Found";
    }
}
