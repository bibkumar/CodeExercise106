package org.bibhav;

import org.bibhav.model.entity.Employee;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Test utility class to provide functions for test data.
 *
 * @author BibhavKumar
 */
public class TestUtility {
    private TestUtility() {
        /* Don't Initialize */
    }

    public static Set<Employee> getEmployeesWithSubOrdinatesSalaryConfigurable(BigDecimal salary) {
        Set<Employee> employees = new HashSet<>();
        Employee employee = new Employee(124L, "Martin", "Chekov", salary, 123L);
        Employee subOrdinate = new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L);
        employee.setSubOrdinates(Set.of(subOrdinate));
        employees.add(employee);
        employees.add(subOrdinate);
        return employees;
    }

    public static Set<Employee> getEmployeesWithCeoAndShortReportingLine() {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), null));
        employees.add(new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L));
        employees.add(new Employee(301L, "Alice", "Hasacat", new BigDecimal("50000"), 300L));
        return employees;
    }

    public static Set<Employee> getEmployeesWithCeoAndLongReportingLine() {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), null));
        employees.add(new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L));
        employees.add(new Employee(301L, "Alice", "Hasacat", new BigDecimal("50000"), 300L));
        employees.add(new Employee(302L, "Alice", "Hasacat", new BigDecimal("50000"), 301L));
        employees.add(new Employee(303L, "Alice", "Hasacat", new BigDecimal("50000"), 302L));
        employees.add(new Employee(304L, "Alice", "Hasacat", new BigDecimal("50000"), 303L));
        employees.add(new Employee(305L, "Alice", "Hasacat", new BigDecimal("50000"), 304L));
        return employees;
    }
}
