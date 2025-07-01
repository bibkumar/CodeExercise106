package org.bibhav.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Employee POJO.
 *
 * @author BibhavKumar
 */
public final class Employee {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final BigDecimal salary;
    private final Long managerId;
    private final Set<Employee> subordinates;

    public Employee(final Long id, final String firstName, final String lastName, final BigDecimal salary, final Long managerId, final Set<Employee> subordinates) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
        this.subordinates = subordinates == null ? Set.of() : Set.copyOf(subordinates);
    }

    public Employee withAddedSubordinates(final Set<Employee> subOrdinates) {
        Set<Employee> newSubordinates = new HashSet<>(this.subordinates);
        newSubordinates.addAll(subOrdinates);
        return new Employee(id, firstName, lastName, salary, managerId, newSubordinates);
    }

    public Long getId() {
        return id;
    }


    public BigDecimal getSalary() {
        return salary;
    }


    public Long getManagerId() {
        return managerId;
    }


    public Set<Employee> getSubordinates() {
        return subordinates;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", managerId=" + managerId +
                ", subOrdinates=" + subordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(salary, employee.salary) && Objects.equals(managerId, employee.managerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, managerId);
    }
}
