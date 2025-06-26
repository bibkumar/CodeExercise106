package org.example.model;

import lombok.Data;

import java.util.Set;

/**
 * Employee POJO.
 *
 * @author BibhavKumar
 */
@Data
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;

    public Employee(Long id, String firstName, String lastName, Double salary, Long managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    private Double salary;
    private Long managerId;
    private Set<Employee> subOrdinates;
}
