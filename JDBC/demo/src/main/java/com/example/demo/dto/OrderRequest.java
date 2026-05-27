package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private LocalDateTime orderDate;
    private Integer employeeId;
    private Integer customerId;

}
