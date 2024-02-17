package com.prashanth.banking.controller;

import com.prashanth.banking.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class BankingController {

    @Autowired private BankingService bankingService;

    @PutMapping("/{id}/deposit")
    ResponseEntity<?> depositMoney(@PathVariable Long id,
                                   @RequestBody Map<String, Double> body) {
        Double amount = body.get("amount");
        return bankingService.deposit(id, amount);
    }

    @PutMapping("/{id}/withdraw")
    ResponseEntity<?> withdrawMoney(@PathVariable Long id,
                                    @RequestBody Map<String, Double> body) {
        Double amount = body.get("amount");
        return bankingService.withdraw(id, amount);
    }
}
