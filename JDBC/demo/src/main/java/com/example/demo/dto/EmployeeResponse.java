package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class EmployeeResponse {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer supervisorId;
}
