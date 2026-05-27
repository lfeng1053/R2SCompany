package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id //Mark as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(length = 10, nullable = false) //first_name field
    private String firstName;

    @Column(length = 20, nullable = false)
    private String lastName;

    private LocalDate birthDate;

    @ManyToOne //Relation between table
    @JoinColumn(name = "supervisor_id") //Chi dinh ten cot FK
    private Employee supervisor;
}
