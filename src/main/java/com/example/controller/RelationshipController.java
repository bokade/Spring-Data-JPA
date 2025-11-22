package com.example.controller;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.service.RelationshipService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/relations")
public class RelationshipController {

    private RelationshipService service;

    public RelationshipController(RelationshipService service) {
        this.service = service;
    }

    // ---------- ONE TO ONE ----------
    @PostMapping("/user")
    public User createUserWithProfile(@RequestBody User user) {
        return service.createUserWithProfile(user);
    }

    // ---------- MANY TO MANY ----------
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public String assignCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return service.assignCourse(studentId, courseId);
    }

    // ---------- FETCH TYPE DEMO ----------
    @GetMapping("/department/{id}/lazy")
    public Department loadLazy(@PathVariable Long id) {
        return service.loadLazy(id);
    }

    @GetMapping("/department/{id}/eager")
    public Department loadEager(@PathVariable Long id) {
        return service.loadEager(id);
    }

    // ---------- LIFECYCLE DEMO ----------
    @PostMapping("/lifecycle/persist")
    public Employee persist(@RequestBody Employee emp) {
        return service.demoPersist(emp);
    }

    @PutMapping("/lifecycle/merge")
    public Employee merge(@RequestBody Employee emp) {
        return service.demoMerge(emp);
    }

    @DeleteMapping("/lifecycle/remove/{id}")
    public String remove(@PathVariable Long id) {
        return service.demoRemove(id);
    }

    @PostMapping("/lifecycle/flush")
    public String flush() {
        service.demoFlush();
        return "Flushed";
    }

    @PostMapping("/lifecycle/clear")
    public String clear() {
        service.demoClear();
        return "Cleared Persistence Context";
    }
}