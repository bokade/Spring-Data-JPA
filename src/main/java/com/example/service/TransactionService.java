package com.example.service;

import com.example.entity.BankAccount;
import com.example.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private final BankAccountRepository repo;

    public TransactionService(BankAccountRepository repo) {
        this.repo = repo;
    }
    // Save new account
    public Long saveAccount(BankAccount account) {
        return repo.save(account).getId();
    }
    // Basic Transaction Example
    @Transactional
    public void updateBalance(Long accountId, double amount) {
        BankAccount acc = repo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        acc.setBalance(acc.getBalance() + amount);
        repo.save(acc);

        // No exception → Commit
        // Exception → Rollback
    }


    @Transactional(rollbackFor = Exception.class)
    public void creditWithRollback(Long id, double amount) throws Exception {
        BankAccount acc = repo.findById(id).orElseThrow();
        acc.setBalance(acc.getBalance() + amount);
        repo.save(acc);

        throw new Exception("Force rollback");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public double readBalance(Long id) {
        return repo.findById(id).orElseThrow().getBalance();
    }


}
