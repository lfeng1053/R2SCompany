package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //Create
    @PostMapping public ResponseEntity<EmployeeResponse> creat(
            @RequestBody EmployeeRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(request));

    }

    //Get all
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    //Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse>getById(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.getById(id));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse>update(
            @PathVariable Integer id,
            @RequestBody EmployeeRequest request
    ){
        return ResponseEntity.ok(employeeService.update(id,request));
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Integer id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
