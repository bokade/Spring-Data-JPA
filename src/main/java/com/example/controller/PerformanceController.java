package com.example.controller;

import com.example.entity.User;
import com.example.service.PerformanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceService service;

    public PerformanceController(PerformanceService service) {
        this.service = service;
    }

    // ------------------ 1. N+1 Problem -------------------
    @GetMapping("/nplusone")
    public List<User> nPlusOne() {
        return service.getUsersNPlusOne();
    }

    @GetMapping("/joinfetch")
    public List<User> joinFetch() {
        return service.getUsersJoinFetch();
    }

    // ------------------ 2. Indexing -------------------
    @GetMapping("/indexing")
    public String indexing() {
        return service.createIndexes();
    }

    // ------------------ 3. Batch Insert -------------------
    @PostMapping("/batch/{userId}/{count}")
    public String batchInsert(@PathVariable Long userId, @PathVariable int count) {
        return service.batchInsertPosts(userId, count);
    }

    // ------------------ 4. Cache Demo -------------------
    @GetMapping("/cache/{id}")
    public User cache(@PathVariable Long id) {
        return service.getUserCached(id);
    }

    // ------------------ 5. Lazy vs Eager -------------------
    @GetMapping("/lazy/{id}")
    public String lazyDemo(@PathVariable Long id) {
        return service.lazyEagerDemo(id);
    }

}
