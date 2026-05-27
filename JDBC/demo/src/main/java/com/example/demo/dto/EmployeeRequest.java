package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;

@Data //Use @Data -> auto gen getter setter toString...
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer supervisorId;
}
