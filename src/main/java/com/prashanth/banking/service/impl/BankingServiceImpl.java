package com.prashanth.banking.service.impl;

import com.prashanth.banking.dto.AccountResponseDTO;
import com.prashanth.banking.entity.Account;
import com.prashanth.banking.entity.Transaction;
import com.prashanth.banking.repository.AccountRepository;
import com.prashanth.banking.repository.TransactionRepository;
import com.prashanth.banking.service.BankingService;
import com.prashanth.banking.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BankingServiceImpl implements BankingService {

    @Autowired private AccountRepository accountRepo;
    @Autowired private TransactionRepository transactionRepo;

    @Override
    public ResponseEntity<?> deposit(Long id, Double amount) {
        try{
            log.info("Inside deposit with id {} and amount {}", id, amount);
            if(amount == null) {
                return new ResponseEntity<>("Amount cannot be empty", HttpStatus.BAD_REQUEST);
            }
            Account account = accountRepo.findByIdAndIsActive(id, 'Y');
            if(account == null) {
                return new ResponseEntity<>(Constants.RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
            Double balance = account.getBalance();
            account.setBalance(account.getBalance() + amount);
            account = accountRepo.saveAndFlush(account);
            transactionRepo.saveAndFlush(Transaction.buildTransaction(balance, amount, "Deposit"));
            AccountResponseDTO response = new AccountResponseDTO(account.getId(), account.getFullName(),
                    account.getBalance(), account.getAccountType(), "Deposited");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred in deposit is ", e);
            return new ResponseEntity<>(Constants.TRANSACTION_FAILED , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> withdraw(Long id, Double amount) {
        try{
            log.info("Inside withdraw with id {} and amount {}", id, amount);
            if(amount == null) {
                return new ResponseEntity<>("Amount cannot be empty", HttpStatus.BAD_REQUEST);
            }
            Account account = accountRepo.findByIdAndIsActive(id, 'Y');
            if(account == null) {
                return new ResponseEntity<>(Constants.RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
            Double balance = account.getBalance();
            if(balance < amount) {
                log.info("Insufficient Balance");
                return new ResponseEntity<>(Constants.INSUFFICIENT_BALANCE, HttpStatus.FORBIDDEN);
            }
            account.setBalance(account.getBalance() - amount);
            account = accountRepo.saveAndFlush(account);
            transactionRepo.saveAndFlush(Transaction.buildTransaction(balance, -amount, "Withdraw"));
            AccountResponseDTO response = new AccountResponseDTO(account.getId(), account.getFullName(),
                    account.getBalance(), account.getAccountType(), "Withdrawed");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred in withdraw is ", e);
            return new ResponseEntity<>(Constants.TRANSACTION_FAILED , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
