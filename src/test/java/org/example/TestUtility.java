package org.example;

import org.example.model.Employee;

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
}
