package com.prashanth.banking.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO implements Serializable {

    private Long id;
    private String fullName;
    private Double balance;
    private String accountType;
    private String message;
}
