package com.example.service;
import com.example.entity.EmployeeDynamic;
import com.example.repository.EmployeeDynamicRepository;
import com.example.spec.EmployeeDynamicSpec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeDynamicService {

    private final EmployeeDynamicRepository repo;

    @PersistenceContext
    private EntityManager em;

    public EmployeeDynamicService(EmployeeDynamicRepository repo) {
        this.repo = repo;
    }

    // ---------------- SAVE ----------------
    public EmployeeDynamic save(EmployeeDynamic emp) {
        return repo.save(emp);
    }

    // ---------------- SPECIFICATION DYNAMIC FILTER ----------------
    public List<EmployeeDynamic> dynamicFilter(
            String name, Integer minAge, Integer maxAge,
            Double minSalary, Double maxSalary, Boolean active) {

        Specification<EmployeeDynamic> spec = Specification
                .where(EmployeeDynamicSpec.nameContains(name))
                .and(EmployeeDynamicSpec.minAge(minAge))
                .and(EmployeeDynamicSpec.maxAge(maxAge))
                .and(EmployeeDynamicSpec.salaryBetween(minSalary, maxSalary))
                .and(EmployeeDynamicSpec.isActive(active));

        return repo.findAll(spec);
    }

    // ---------------- CRITERIA API ----------------
    public List<EmployeeDynamic> criteriaFindByEmail(String email) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(EmployeeDynamic.class);
        var root = cq.from(EmployeeDynamic.class);

        cq.select(root)
                .where(cb.equal(root.get("email"), email));

        return em.createQuery(cq).getResultList();
    }

    public List<EmployeeDynamic> criteriaSalaryGreater(Double salary) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(EmployeeDynamic.class);
        var root = cq.from(EmployeeDynamic.class);

        cq.select(root)
                .where(cb.greaterThan(root.get("salary"), salary));

        return em.createQuery(cq).getResultList();
    }
}