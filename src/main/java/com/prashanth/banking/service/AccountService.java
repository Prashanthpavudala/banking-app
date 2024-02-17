package com.prashanth.banking.service;

import com.prashanth.banking.requests.AccountDTO;
import com.prashanth.banking.requests.BranchDTO;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<?> createAccount(AccountDTO accountDTO);

    ResponseEntity<?> getAccountById(Long id);

    ResponseEntity<?> getAllAccounts();

    ResponseEntity<?> disableAccountById(Long id);

    ResponseEntity<?> enableAccountById(Long id);

    ResponseEntity<?> deleteAccountById(Long id);

    ResponseEntity<?> createBranch(BranchDTO branchDTO);
}
