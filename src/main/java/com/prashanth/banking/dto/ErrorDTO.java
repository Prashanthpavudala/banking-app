package com.prashanth.banking.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {

    private String flag;
    private String message;
}
