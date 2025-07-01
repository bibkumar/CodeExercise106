package org.bibhav.service;

import org.bibhav.TestUtility;
import org.bibhav.exception.ApplicationException;
import org.bibhav.model.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(45000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertEquals(Double.valueOf(15000), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getUnderpaidManagersWithDisparity_empty() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(61000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }

    @Test
    void getOverpaidManagersWithDisparity() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(76000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertEquals(Double.valueOf(1000), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getOverpaidManagersWithDisparity_empty() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(61000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }

    @Test
    void getEmployeesWithTooLongReportingLine_absent() throws ApplicationException {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndShortReportingLine();
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);

        assertTrue(employeesWithTooLongReportingLine.isEmpty());
    }

    @Test
    void getEmployeesWithTooLongReportingLine_present() throws ApplicationException {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndLongReportingLine();
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);

        assertFalse(employeesWithTooLongReportingLine.isEmpty());
        assertEquals(1, employeesWithTooLongReportingLine.get(305L));
    }
}