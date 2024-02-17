package com.prashanth.banking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TRANSACTION_AMOUNT")
    private Double transactionAmount;

    @Column(name = "FINAL_AMOUNT")
    private Double finalAmount;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "CREATEDATE")
    private Timestamp createDate;

    @Column(name = "MODIDATE")
    private Timestamp modiDate;

    @Column(name = "ISACTIVE")
    private Character isActive;

    @Column(name = "DELETED")
    private Character deleted;

    public static Transaction buildTransaction(Double initalAmount, Double transactionAmount, String notes) {
        return Transaction.builder()
                .transactionAmount(transactionAmount)
                .finalAmount(initalAmount+transactionAmount)
                .notes(notes)
                .createDate(new Timestamp(System.currentTimeMillis()))
                .modiDate(new Timestamp(System.currentTimeMillis()))
                .isActive('Y')
                .deleted('N')
                .build();
    }
}
