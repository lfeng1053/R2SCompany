package com.example.demo.service.impl;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepo;
    private final OrderRepository orderRepo;
    private final EmployeeRepository employeeRepo;

    @Override
    public OrderResponse create(OrderRequest req) {
        Customer c = customerRepo.findById(req.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found:" + req.getCustomerId()));
        Employee e = employeeRepo.findById(req.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found:" + req.getEmployeeId()));

        Order o = new Order();
        //o.setOrderId();
        o.setCustomer(c);
        o.setEmployee(e);
        o.setOrderDate(req.getOrderDate() != null ? req.getOrderDate() : LocalDateTime.now());
        return map(orderRepo.save(o));
    }

    @Override
    public OrderResponse update(Integer id, OrderRequest req) {
        Order o = orderRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found:" + id));
        Customer c = customerRepo.findById(req.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found:" + req.getCustomerId()));
        Employee e = employeeRepo.findById(req.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found:" + req.getEmployeeId()));
        o.setCustomer(c);
        o.setEmployee(e);
        if(req.getOrderDate() != null) o.setOrderDate(req.getOrderDate());
        return map(orderRepo.save(o));
    }

    @Override
    public void delete(Integer id) {
        if (!orderRepo.existsById(id)) throw new ResourceNotFoundException("Order not found: " + id);
        orderRepo.deleteById(id);
    }

    @Override
    public OrderResponse getById(Integer id) {
        Order o = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found:" + id));
        return map(o);
    }

    @Override
    // If you need details, create a JPQL join fetch for all (paged recommended)
    public List<OrderResponse> getAll() {
        return List.of();
    }

    @Override
    public List<OrderResponse> getByEmployee(Integer employeeId) {
        return orderRepo.findOrderWithEmployeeDetail(employeeId)
                .stream().map(this::map).toList();
    }

    @Override
    public List<OrderResponse> getBetween(LocalDateTime from, LocalDateTime to) {
        return orderRepo.findOrderWithDetailBetween(from, to)
                .stream().map(this::map).toList();
    }

    private OrderResponse map(Order o){
        String empName = (o.getEmployee() != null)
                ? o.getEmployee().getFirstName() + " " + o.getEmployee().getLastName()
                : null;
        return new OrderResponse(
                o.getOrderId(),
                o.getOrderDate(),
                o.getCustomer() != null ? o.getCustomer().getCustomerId() : null,
                o.getCustomer() != null ? o.getCustomer().getCustomerName() : null,
                o.getEmployee() != null ? o.getEmployee().getEmployeeId() : null,
                empName
        );
    }
}
