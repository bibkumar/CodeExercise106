package org.bibhav;

import org.bibhav.model.Employee;

import java.util.HashSet;
import java.util.Set;

/**
 * Test utility class.
 *
 * @author BibhavKumar
 */
public class TestUtility {
    private TestUtility() {
        /* Don't Initialize */
    }

    public static Set<Employee> getEmployeesWithSubOrdinatesSalaryConfigurable(double salary) {
        Set<Employee> employees = new HashSet<>();
        Employee employee = new Employee(124L, "Martin", "Chekov", salary, 123L);
        Employee subOrdinate = new Employee(300L, "Alice", "Hasacat", 50000D, 124L);
        employee.setSubOrdinates(Set.of(subOrdinate));
        employees.add(employee);
        employees.add(subOrdinate);
        return employees;
    }

    public static Set<Employee> getEmployeesWithCeoAndShortReportingLine() {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(124L, "Martin", "Chekov", 45000D, null));
        employees.add(new Employee(300L, "Alice", "Hasacat", 50000D, 124L));
        employees.add(new Employee(301L, "Alice", "Hasacat", 50000D, 300L));
        return employees;
    }

    public static Set<Employee> getEmployeesWithCeoAndLongReportingLine() {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(124L, "Martin", "Chekov", 45000D, null));
        employees.add(new Employee(300L, "Alice", "Hasacat", 50000D, 124L));
        employees.add(new Employee(301L, "Alice", "Hasacat", 50000D, 300L));
        employees.add(new Employee(302L, "Alice", "Hasacat", 50000D, 301L));
        employees.add(new Employee(303L, "Alice", "Hasacat", 50000D, 302L));
        employees.add(new Employee(304L, "Alice", "Hasacat", 50000D, 303L));
        employees.add(new Employee(305L, "Alice", "Hasacat", 50000D, 304L));
        return employees;
    }
}
