package com.example.demo.service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;

import java.util.List;

public  interface EmployeeService {
    List<EmployeeResponse> getAll();
    EmployeeResponse getById(Integer id);
    EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse update(Integer id, EmployeeRequest request);
    void delete(Integer id);

}
