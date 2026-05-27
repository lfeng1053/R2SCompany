package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
public class OrderResponse {
    private Integer orderId;
    private LocalDateTime orderDate;
    private Integer customerId;
    private String customerName;
    private Integer employeeId;
    private String empName;


}
