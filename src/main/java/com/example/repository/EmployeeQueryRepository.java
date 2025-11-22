package com.example.repository;

import com.example.entity.EmployeeQuery;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeQueryRepository extends JpaRepository<EmployeeQuery, Long> {

    // ---------------- JPQL ----------------

    @Query("SELECT e FROM EmployeeQuery e WHERE e.email = :email")
    EmployeeQuery findByEmailJPQL(@Param("email") String email);

    @Query("SELECT e FROM EmployeeQuery e WHERE e.age > :age")
    List<EmployeeQuery> getAgeGreaterJPQL(@Param("age") Integer age);


    // ---------------- JPQL LIKE ----------------
    @Query("SELECT e FROM EmployeeQuery e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :key, '%'))")
    List<EmployeeQuery> searchNameJPQL(@Param("key") String key);


    // ---------------- NATIVE QUERIES ----------------
    @Query(value = "SELECT * FROM employee_query WHERE salary BETWEEN ?1 AND ?2", nativeQuery = true)
    List<EmployeeQuery> salaryBetweenNative(Double min, Double max);


    @Query(value = "SELECT * FROM employee_query WHERE active = true", nativeQuery = true)
    List<EmployeeQuery> activeEmployeesNative();


    // ---------------- UPDATE QUERY (JPQL) ----------------

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeQuery e SET e.salary = :salary WHERE e.id = :id")
    int updateSalary(@Param("id") Long id, @Param("salary") Double salary);


    // ---------------- DELETE QUERY (JPQL) ----------------

    @Modifying
    @Transactional
    @Query("DELETE FROM EmployeeQuery e WHERE e.email = :email")
    int deleteByEmailJPQL(@Param("email") String email);
}
