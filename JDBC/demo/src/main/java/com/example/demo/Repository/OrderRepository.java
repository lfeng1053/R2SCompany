package com.example.demo.Repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
    //Find by time
    @Query("""
        SELECT o FROM Order o 
        JOIN FETCH o.customer c
        JOIN FETCH o.employee e
        WHERE o.orderDate BETWEEN :from AND :to
""")
    List<Order> findOrderWithDetailBetween(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    //Find by employee detail
    @Query("""
        SELECT o FROM Order o
        JOIN FETCH o.customer c
        JOIN FETCH o.employee e
        WHERE e.employeeId = :emplId
""")
    List <Order> findOrderWithEmployeeDetail(
            @Param("emplId") Integer emplId
    );



}
