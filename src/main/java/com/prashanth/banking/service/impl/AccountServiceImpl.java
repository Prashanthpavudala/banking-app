package com.prashanth.banking.service.impl;

import com.prashanth.banking.requests.AccountDTO;
import com.prashanth.banking.dto.AccountResponseDTO;
import com.prashanth.banking.requests.BranchDTO;
import com.prashanth.banking.dto.ErrorDTO;
import com.prashanth.banking.entity.Account;
import com.prashanth.banking.entity.Branch;
import com.prashanth.banking.entity.Transaction;
import com.prashanth.banking.repository.AccountRepository;
import com.prashanth.banking.repository.BranchRepository;
import com.prashanth.banking.repository.TransactionRepository;
import com.prashanth.banking.service.AccountService;
import com.prashanth.banking.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired private AccountRepository accountRepo;
    @Autowired private TransactionRepository transactionRepo;
    @Autowired private BranchRepository branchRepo;

    @Override
    public ResponseEntity<?> createAccount(AccountDTO accountDTO) {
        try{
            log.info("Inside createAccount with request {}", accountDTO);
            if(isAccountExist(accountDTO.getMobile())) {
                log.error("Account already exists!!!");
                ErrorDTO error = new ErrorDTO(Constants.FAIL, Constants.ACCOUNT_ALREADY_EXISTS);
                return new ResponseEntity<>(error , HttpStatus.CONFLICT);
            }
            Branch branch = branchRepo.findTopByBranchIdAndIsActiveOrderByCreateDateDesc(accountDTO.getBranchId(), 'Y');
            if(branch == null) {
                log.error("invalid branch code");
                ErrorDTO error = new ErrorDTO(Constants.FAIL, Constants.INVALID_BRANCH);
                return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
            }
            Account account = accountRepo.saveAndFlush(Account.buildAccount(accountDTO));
            transactionRepo.saveAndFlush(Transaction.buildTransaction(0.0, accountDTO.getOpeningAmount(), "Opening Balance while account creation"));
            AccountResponseDTO response = new AccountResponseDTO(account.getId(), account.getFullName(),
                    account.getBalance(), account.getAccountType(), Constants.ACCOUNT_CREATION_SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e) {
            log.error("Exception occurred in createAccount is ", e);
            return new ResponseEntity<>(Constants.ACCOUNT_CREATION_FAIL , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAccountById(Long id) {
        Account account = accountRepo.findByIdAndIsActive(id, 'Y');
        if(account == null) {
            return new ResponseEntity<>(Constants.RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        AccountResponseDTO response = new AccountResponseDTO(account.getId(), account.getFullName(),
                account.getBalance(), account.getAccountType(), Constants.RECORD_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllAccounts() {
        try{
            log.info("Inside getAllAccounts");
            List<Account> accountList = accountRepo.findAllByIsActive('Y');
            List<AccountResponseDTO> response = accountList.stream().map(account -> new AccountResponseDTO(account.getId(), account.getFullName(),
                    account.getBalance(), account.getAccountType(), Constants.RECORD_FOUND)).toList();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred in getAllAccounts is ", e);
            return new ResponseEntity<>(Constants.FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> disableAccountById(Long id) {
        log.info("Inside disableAccountById with request id {}", id);
        Account account = accountRepo.findByIdAndIsActive(id, 'Y');
        if(account == null) {
            return new ResponseEntity<>(Constants.RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        account.setIsActive('N');
        account.setDeleted('Y');
        accountRepo.saveAndFlush(account);
        return new ResponseEntity<>(Constants.ACCOUNT_DISABLED, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> enableAccountById(Long id) {
        log.info("Inside deleteAccountById with request id {}", id);
        Account account = accountRepo.findByIdAndIsActive(id, 'Y');
        if(account == null) {
            return new ResponseEntity<>(Constants.RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        account.setIsActive('Y');
        account.setDeleted('N');
        accountRepo.saveAndFlush(account);
        return new ResponseEntity<>(Constants.ACCOUNT_ENABLED, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAccountById(Long id) {
        log.info("Inside deleteAccountById with request id {}", id);
        Account account = accountRepo.findByIdAndIsActive(id, 'Y');
        if(account == null) {
            return new ResponseEntity<>(Constants.RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        account.setIsActive('N');
        account.setDeleted('Y');
        accountRepo.saveAndFlush(account);
        return new ResponseEntity<>(Constants.ACCOUNT_DELETED, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createBranch(BranchDTO branchDTO) {
        try{
            log.info("Inside createBranch with request {}", branchDTO);
            if(isBranchExist(branchDTO.getIfsc())) {
                log.error("Branch already exists!!!");
                ErrorDTO error = new ErrorDTO(Constants.FAIL, Constants.BRANCH_ALREADY_EXISTS);
                return new ResponseEntity<>(error , HttpStatus.CONFLICT);
            }
            branchRepo.saveAndFlush(Branch.buildBranch(branchDTO));
            return new ResponseEntity<>(Constants.BRANCH_CREATION_SUCCESS , HttpStatus.CREATED);
        }catch (Exception e) {
            log.error("Exception occurred in createBranch is ", e);
            return new ResponseEntity<>(Constants.BRANCH_CREATION_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Boolean isAccountExist(String mobile) {
        Account account = accountRepo.findTopByMobileAndIsActiveOrderByCreateDateDesc(mobile, 'Y');
        return account != null;
    }

    private Boolean isBranchExist(String ifsc) {
        Branch branch = branchRepo.findTopByIfscAndIsActiveOrderByCreateDateDesc(ifsc, 'Y');
        return branch != null;
    }

}
