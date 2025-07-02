package org.bibhav.service;

import org.bibhav.model.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for EmployeeReportingLineCalculationService.
 * <p>
 * This class contains unit tests for the EmployeeReportingLineCalculationService, ensuring that it correctly calculates
 * employee reporting lines based on their relationships. It uses JUnit 5 for testing and includes assertions to verify
 * expected outcomes.
 * </p>
 *
 * @author BibhavKumar
 */
class EmployeeReportingLineCalculationServiceTest {

    private static EmployeeReportingLineCalculationService employeeReportingLineCalculationService;

    @BeforeEach
    void setUp() {
        employeeReportingLineCalculationService = new EmployeeReportingLineCalculationService();
    }

    @Test
    void getEmployeeReportingLine_shortReportingLine() {
        Set<Employee> employees1 = new HashSet<>();
        employees1.add(new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), null, null));
        employees1.add(new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null));
        employees1.add(new Employee(301L, "Alice", "Hasacat", new BigDecimal("50000"), 300L, null));
        Set<Employee> employees = employees1;
        Map<Long, List<Long>> employeeReportingLine = employeeReportingLineCalculationService.getEmployeeIdAndReportingLineListMap(employees);

        assertEquals(2, employeeReportingLine.size());
        List<Long> managerLine = employeeReportingLine.get(301L);
        assertEquals(1, managerLine.size());
        assertTrue(managerLine.contains(300L));
    }

    @Test
    void getEmployeeReportingLine_longReportingLine() {
        Set<Employee> employees1 = new HashSet<>();
        employees1.add(new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), null, null));
        employees1.add(new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null));
        employees1.add(new Employee(301L, "Alice", "Hasacat", new BigDecimal("50000"), 300L, null));
        employees1.add(new Employee(302L, "Alice", "Hasacat", new BigDecimal("50000"), 301L, null));
        employees1.add(new Employee(303L, "Alice", "Hasacat", new BigDecimal("50000"), 302L, null));
        employees1.add(new Employee(304L, "Alice", "Hasacat", new BigDecimal("50000"), 303L, null));
        employees1.add(new Employee(305L, "Alice", "Hasacat", new BigDecimal("50000"), 304L, null));
        Set<Employee> employees = employees1;
        Map<Long, List<Long>> employeeReportingLine = employeeReportingLineCalculationService.getEmployeeIdAndReportingLineListMap(employees);

        assertEquals(6, employeeReportingLine.size());
        List<Long> managerLine = employeeReportingLine.get(305L);
        assertEquals(5, managerLine.size());
        assertTrue(managerLine.contains(300L));
        assertTrue(managerLine.contains(301L));
        assertTrue(managerLine.contains(302L));
        assertTrue(managerLine.contains(303L));
        assertTrue(managerLine.contains(304L));
    }
}