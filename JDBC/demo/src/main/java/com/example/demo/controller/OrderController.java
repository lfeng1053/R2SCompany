package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));

    }
    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Integer id){
        return service.getById(id);
    }

    @GetMapping
    public List<OrderResponse> getAll() {
        return service.getAll();
    }
    @GetMapping("/by-employee/{employeeId}")
    public List<OrderResponse> byEmployee(@PathVariable Integer employeeId) {
        return service.getByEmployee(employeeId);
    }
    @GetMapping("/between")
    public List<OrderResponse> between(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return service.getBetween(from, to);
    }
    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Integer id, @Valid @RequestBody OrderRequest req) {
        return service.update(id, req);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
