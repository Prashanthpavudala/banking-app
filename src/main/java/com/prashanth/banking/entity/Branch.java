package com.prashanth.banking.entity;

import com.prashanth.banking.requests.BranchDTO;
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
@Table(name = "branch")
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BRANCH_ID")
    private Long branchId;

    @Column(name = "IFSC_CODE")
    private String ifsc;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "CREATEDATE")
    private Timestamp createDate;

    @Column(name = "MODIDATE")
    private Timestamp modiDate;

    @Column(name = "ISACTIVE")
    private Character isActive;

    @Column(name = "DELETED")
    private Character deleted;

    public static Branch buildBranch(BranchDTO branchDTO) {
        return Branch.builder()
                .ifsc(branchDTO.getIfsc())
                .location(branchDTO.getLocation())
                .createDate(new Timestamp(System.currentTimeMillis()))
                .modiDate(new Timestamp(System.currentTimeMillis()))
                .isActive('Y')
                .deleted('N')
                .build();
    }
}
