package com.example.demo.service;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest req);
    OrderResponse update (Integer id, OrderRequest req);
    void delete(Integer id);
    OrderResponse getById(Integer id);
    List<OrderResponse> getAll();
    List<OrderResponse> getByEmployee(Integer employeeId);
    List<OrderResponse> getBetween(LocalDateTime from, LocalDateTime to);
}
