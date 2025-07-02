package org.bibhav.service;

import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ReportService.
 * <p>
 * This class contains unit tests for the ReportService, ensuring that it correctly generates reports based on employee
 * and manager data. It uses JUnit 5 for testing and includes assertions to verify expected outcomes.
 * </p>
 *
 * @author BibhavKumar
 */
class ReportServiceTest {

    private static ReportService reportService;

    @BeforeEach
    void setUp() {
        ManagerSalaryComparisonService managerComparisonService = new ManagerSalaryComparisonService();
        EmployeeReportingLineCalculationService employeeReportingLineCalculationService = new EmployeeReportingLineCalculationService();
        reportService = new ReportService(managerComparisonService, employeeReportingLineCalculationService);
    }

    @Test
    void getUnderpaidManagersWithDisparity() {
        Set<Employee> employees1 = new HashSet<>();
        Employee subOrdinate = new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null);
        Employee employee = new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), 123L, Set.of(subOrdinate));
        employees1.add(employee);
        employees1.add(subOrdinate);
        Set<Employee> employees = employees1;
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertEquals(new BigDecimal("15000.00"), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getUnderpaidManagersWithDisparity_empty() {
        Set<Employee> employees1 = new HashSet<>();
        Employee subOrdinate = new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null);
        Employee employee = new Employee(124L, "Martin", "Chekov", new BigDecimal("61000"), 123L, Set.of(subOrdinate));
        employees1.add(employee);
        employees1.add(subOrdinate);
        Set<Employee> employees = employees1;
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }

    @Test
    void getOverpaidManagersWithDisparity() {
        Set<Employee> employees1 = new HashSet<>();
        Employee subOrdinate = new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null);
        Employee employee = new Employee(124L, "Martin", "Chekov", new BigDecimal("76000"), 123L, Set.of(subOrdinate));
        employees1.add(employee);
        employees1.add(subOrdinate);
        Set<Employee> employees = employees1;
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertEquals(new BigDecimal("1000.00"), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getOverpaidManagersWithDisparity_empty() {
        Set<Employee> employees1 = new HashSet<>();
        Employee subOrdinate = new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null);
        Employee employee = new Employee(124L, "Martin", "Chekov", new BigDecimal("61000"), 123L, Set.of(subOrdinate));
        employees1.add(employee);
        employees1.add(subOrdinate);
        Set<Employee> employees = employees1;
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }

    @Test
    void getEmployeesWithTooLongReportingLine_absent() throws BadRequestException {
        Set<Employee> employees1 = new HashSet<>();
        employees1.add(new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), null, null));
        employees1.add(new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null));
        employees1.add(new Employee(301L, "Alice", "Hasacat", new BigDecimal("50000"), 300L, null));
        Set<Employee> employees = employees1;
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);

        assertTrue(employeesWithTooLongReportingLine.isEmpty());
    }

    @Test
    void getEmployeesWithTooLongReportingLine_present() throws BadRequestException {
        Set<Employee> employees1 = new HashSet<>();
        employees1.add(new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), null, null));
        employees1.add(new Employee(300L, "Alice", "Hasacat", new BigDecimal("50000"), 124L, null));
        employees1.add(new Employee(301L, "Alice", "Hasacat", new BigDecimal("50000"), 300L, null));
        employees1.add(new Employee(302L, "Alice", "Hasacat", new BigDecimal("50000"), 301L, null));
        employees1.add(new Employee(303L, "Alice", "Hasacat", new BigDecimal("50000"), 302L, null));
        employees1.add(new Employee(304L, "Alice", "Hasacat", new BigDecimal("50000"), 303L, null));
        employees1.add(new Employee(305L, "Alice", "Hasacat", new BigDecimal("50000"), 304L, null));
        Set<Employee> employees = employees1;
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);

        assertFalse(employeesWithTooLongReportingLine.isEmpty());
        assertEquals(1, employeesWithTooLongReportingLine.get(305L));
    }
}