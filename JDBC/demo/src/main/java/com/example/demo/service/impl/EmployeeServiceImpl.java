package com.example.demo.service.impl;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.*;

import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository =employeeRepository;
}


    //Get all
    @Override
    @Transactional
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    public EmployeeResponse getById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not found with id = " + id));
        return toResponse(employee);
    }

//Create
    @Override
    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setBirthDate(request.getBirthDate());
        //--------------------------------------------------------------
        if (request.getSupervisorId() != null) {
            Employee supervisor = employeeRepository.findById(request.getSupervisorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id = " + request.getSupervisorId()));
            employee.setSupervisor(supervisor);
        }
        //-------------------------------------------------------
        Employee saved = employeeRepository.save(employee);
        return toResponse(saved);
    }

    //Update
    @Override

    public EmployeeResponse update(Integer id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id =" +id));
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setBirthDate(request.getBirthDate());
        if (request.getSupervisorId() != null) {

            Employee supervisor = employeeRepository.findById(request.getSupervisorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id=" + request.getSupervisorId()));
            employee.setSupervisor(supervisor);
        } else {
            employee.setSupervisor(null);
        }

        Employee updated = employeeRepository.save(employee);
        return toResponse(updated);

    }

    //Delete
    @Override
    public void delete(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id ="+id));
        employeeRepository.delete(employee);

    }

    private EmployeeResponse toResponse (Employee e){
        EmployeeResponse res = new EmployeeResponse();
        res.setEmployeeId(e.getEmployeeId());
        res.setFirstName(e.getFirstName());
        res.setLastName(e.getLastName());
        res.setBirthDate(e.getBirthDate());
        if (e.getSupervisor() != null) {
            res.setSupervisorId(e.getSupervisor().getEmployeeId());
        } else {
            res.setSupervisorId(null);
        }
        return res;
    }
}
