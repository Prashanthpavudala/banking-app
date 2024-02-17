package com.prashanth.banking.controller;

import com.prashanth.banking.requests.AccountDTO;
import com.prashanth.banking.requests.BranchDTO;
import com.prashanth.banking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired private AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<?> disableAccount(@PathVariable Long id) {
        return accountService.disableAccountById(id);
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<?> enableAccount(@PathVariable Long id) {
        return accountService.enableAccountById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return accountService.deleteAccountById(id);
    }

    @PostMapping("/create-branch")
    public ResponseEntity<?> createBranch(@Valid @RequestBody BranchDTO branchDTO) {
        return accountService.createBranch(branchDTO);
    }

}
