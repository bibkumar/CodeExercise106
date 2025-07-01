package org.bibhav.service;

import org.bibhav.TestUtility;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test functions of reporting service.
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
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(new BigDecimal("45000"));
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertEquals(new BigDecimal("15000.00"), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getUnderpaidManagersWithDisparity_empty() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(new BigDecimal("61000"));
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }

    @Test
    void getOverpaidManagersWithDisparity() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(new BigDecimal("76000"));
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertEquals(new BigDecimal("1000.00"), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getOverpaidManagersWithDisparity_empty() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(new BigDecimal("61000"));
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }

    @Test
    void getEmployeesWithTooLongReportingLine_absent() throws BadRequestException {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndShortReportingLine();
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);

        assertTrue(employeesWithTooLongReportingLine.isEmpty());
    }

    @Test
    void getEmployeesWithTooLongReportingLine_present() throws BadRequestException {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndLongReportingLine();
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);

        assertFalse(employeesWithTooLongReportingLine.isEmpty());
        assertEquals(1, employeesWithTooLongReportingLine.get(305L));
    }
}