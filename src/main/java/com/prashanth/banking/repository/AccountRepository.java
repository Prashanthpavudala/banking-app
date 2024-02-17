package com.prashanth.banking.repository;

import com.prashanth.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByIdAndIsActive(Long id, Character isActive);

    Account findTopByMobileAndIsActiveOrderByCreateDateDesc(String mobile, Character isActive);

    List<Account> findAllByIsActive(Character isActive);
}
