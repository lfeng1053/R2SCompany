package com.example.demo.Repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //Derived Query
    List<Customer> findByCity(String city);
    List<Customer> findByCustomerNameContainingIgnoreCase(String keyword);

    //JPQL
    @Query("SELECT c FROM Customer c WHERE c.country =:country")
    List<Customer> findCustomerByCountry(@Param("country") String country);
}
