package org.bibhav.model;

import java.util.Objects;
import java.util.Set;

/**
 * Employee POJO.
 *
 * @author BibhavKumar
 */
public class Employee {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Double salary;
    private final Long managerId;
    private Set<Employee> subOrdinates;

    public Employee(final Long id, final String firstName, final String lastName, final Double salary, final Long managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }


    public Double getSalary() {
        return salary;
    }


    public Long getManagerId() {
        return managerId;
    }


    public Set<Employee> getSubOrdinates() {
        return subOrdinates;
    }

    public void setSubOrdinates(Set<Employee> subOrdinates) {
        this.subOrdinates = subOrdinates;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", managerId=" + managerId +
                ", subOrdinates=" + subOrdinates +
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
