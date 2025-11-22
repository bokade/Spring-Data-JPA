package com.example.service;

import com.example.entity.*;
import com.example.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RelationshipService {

    @PersistenceContext
    private EntityManager em;

    private UserRepository userRepo;
    private StudentRepository studentRepo;
    private CourseRepository courseRepo;
    private DepartmentRepository deptRepo;
    private EmployeeRepository empRepo;

    public RelationshipService(
            UserRepository userRepo, StudentRepository studentRepo,
            CourseRepository courseRepo, DepartmentRepository deptRepo,
            EmployeeRepository empRepo) {
        this.userRepo = userRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.deptRepo = deptRepo;
        this.empRepo = empRepo;
    }

    // ------------------- ONE TO ONE -------------------
    public User createUserWithProfile(User user) {
        user.getProfile().setUser(user); // set both sides
        return userRepo.save(user);
    }

    // ------------------- MANY TO MANY -------------------
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public String assignCourse(Long studentId, Long courseId) {
        Student s = studentRepo.findById(studentId).orElseThrow();
        Course c = courseRepo.findById(courseId).orElseThrow();
        s.getCourses().add(c);
        studentRepo.save(s);
        return "Course Assigned";
    }

    // ------------------- FETCH TYPE -------------------
    public Department loadLazy(Long id) {
        return deptRepo.findById(id).orElseThrow();
    }

    public Department loadEager(Long id) {
        Department d = deptRepo.findById(id).orElseThrow();
        d.getEmployees().size();  // force initialization
        return d;
    }

    // ------------------- LIFECYCLE -------------------
    public Employee demoPersist(Employee emp) {
        em.persist(emp);   // NEW → MANAGED
        return emp;
    }

    public Employee demoMerge(Employee emp) {
        return em.merge(emp); // merge returns MANAGED copy
    }

    public void demoFlush() {
        em.flush(); // forces SQL execution
    }

    public void demoClear() {
        em.clear(); // persistence context empty
    }

    public String demoRemove(Long id) {
        Employee emp = em.find(Employee.class, id);
        em.remove(emp);
        return "Removed";
    }
}
