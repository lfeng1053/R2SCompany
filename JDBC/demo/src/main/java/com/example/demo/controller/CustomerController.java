package com.example.demo.controller;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @RequestMapping("/api/v1/customers")
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping("/api/v1/customers/{id}")
    public CustomerResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/api/v1/customers")
    public List<CustomerResponse> getAll() {
        return service.getAll();
    }
    @GetMapping("/api/v1/customers/search")
    public List<CustomerResponse> search(@RequestParam String name){
        return service.searchByName(name);
    }

    @PutMapping("/api/v1/customers/{id}")
    public CustomerResponse update(
            @PathVariable Integer id,
            @Valid @RequestBody CustomerRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/api/v1/customers/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


    }

