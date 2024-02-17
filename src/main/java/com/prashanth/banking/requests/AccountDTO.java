package com.prashanth.banking.requests;

import jakarta.validation.constraints.*;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {

    @NotBlank(message = "Full name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Full name should contain only letters and spaces")
    private String fullName;

    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String mobile;

    @NotBlank(message = "Email cannot be empty")
    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    private String email;

    @NotBlank(message = "Aadhaar number cannot be empty")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Aadhaar number should be 12 digits")
    private String aadhaarNo;

    @NotBlank(message = "Pan card number cannot be empty")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$", message = "Invalid PAN card format")
    private String panCardNo;

    @NotNull(message = "Branch ID cannot be empty")
    private Long branchId;

    @NotNull(message = "Opening amount cannot be empty")
    @Positive(message = "Opening amount must be positive")
    private Double openingAmount;

    @NotBlank(message = "Account Type cannot be empty")
    private String accountType;
}
