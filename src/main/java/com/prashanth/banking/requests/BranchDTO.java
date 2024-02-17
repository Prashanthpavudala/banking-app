package com.prashanth.banking.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO implements Serializable {

    @NotBlank(message = "IFSC code cannot be blank")
    @Pattern(regexp = "^[A-Z]{4}\\d{7}$", message = "Invalid IFSC code format")
    private String ifsc;

    @NotBlank(message = "Location cannot be blank")
    private String location;
}
