package com.prashanth.banking.repository;

import com.prashanth.banking.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch findTopByBranchIdAndIsActiveOrderByCreateDateDesc(Long branchId, Character isActive);

    Branch findTopByIfscAndIsActiveOrderByCreateDateDesc(String ifsc, Character isActive);
}
