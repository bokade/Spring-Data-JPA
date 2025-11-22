package com.example.controller;

import com.example.entity.BankAccount;
import com.example.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tx")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public String saveAccount(@RequestBody BankAccount account) {
        Long id = service.saveAccount(account);
        return "Account created with ID: " + id;
    }

    // 1️⃣ Basic update
    @PostMapping("/update/{id}/{amount}")
    public String update(@PathVariable Long id, @PathVariable double amount) {
        service.updateBalance(id, amount);
        return "Balance updated";
    }

    // 2️⃣ Rollback test
    @PostMapping("/rollback/{id}/{amount}")
    public String rollback(@PathVariable Long id, @PathVariable double amount) throws Exception {
        service.creditWithRollback(id, amount);
        return "This will never execute";
    }

    // 3️⃣ Read with specific isolation
    @GetMapping("/balance/{id}")
    public double read(@PathVariable Long id) {
        return service.readBalance(id);
    }
}
