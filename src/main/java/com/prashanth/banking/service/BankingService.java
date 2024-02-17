package com.prashanth.banking.service;

import org.springframework.http.ResponseEntity;

public interface BankingService {

    ResponseEntity<?> deposit(Long id, Double amount);

    ResponseEntity<?> withdraw(Long id, Double amount);
}
