package com.prashanth.banking.entity;

import com.prashanth.banking.requests.AccountDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "MOBILE_NO")
    private String mobile;

    @Column(name = "EMAIL_ID")
    private String email;

    @Column(name = "AADHAAR_NO")
    private String aadhaarNo;

    @Column(name = "PANCARD_NO")
    private String panCardNo;

    @Column(name = "BRANCH_CODE")
    private Long branchCode;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "CREATEDATE")
    private Timestamp createDate;

    @Column(name = "MODIDATE")
    private Timestamp modiDate;

    @Column(name = "ISACTIVE")
    private Character isActive;

    @Column(name = "DELETED")
    private Character deleted;

    public static Account buildAccount(AccountDTO accountDTO) {
        return Account.builder()
                .fullName(accountDTO.getFullName())
                .mobile(accountDTO.getMobile())
                .email(accountDTO.getEmail())
                .aadhaarNo(accountDTO.getAadhaarNo())
                .panCardNo(accountDTO.getPanCardNo())
                .branchCode(accountDTO.getBranchId())
                .balance(accountDTO.getOpeningAmount())
                .accountType(accountDTO.getAccountType())
                .createDate(new Timestamp(System.currentTimeMillis()))
                .modiDate(new Timestamp(System.currentTimeMillis()))
                .isActive('Y')
                .deleted('N')
                .build();
    }
}
