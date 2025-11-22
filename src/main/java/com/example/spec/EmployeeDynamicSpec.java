package com.example.spec;

import com.example.entity.EmployeeDynamic;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeDynamicSpec {

    public static Specification<EmployeeDynamic> nameContains(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<EmployeeDynamic> minAge(Integer age) {
        return (root, query, cb) ->
                age == null ? null : cb.greaterThanOrEqualTo(root.get("age"), age);
    }

    public static Specification<EmployeeDynamic> maxAge(Integer age) {
        return (root, query, cb) ->
                age == null ? null : cb.lessThanOrEqualTo(root.get("age"), age);
    }

    public static Specification<EmployeeDynamic> salaryBetween(Double min, Double max) {
        return (root, query, cb) -> {
            if (min == null || max == null) return null;
            return cb.between(root.get("salary"), min, max);
        };
    }

    public static Specification<EmployeeDynamic> isActive(Boolean active) {
        return (root, query, cb) ->
                active == null ? null : cb.equal(root.get("active"), active);
    }
}
